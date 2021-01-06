package com.lcy.util.file.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.lcy.controller.common.ServiceException;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.fast.ByteToInputStream;
import com.lcy.util.file.fast.FastResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * 文件上传工具类
 *
 * 双重校验锁-线程安全
 * @author shaolong@linewell.com
 * 
 *         2018年12月13日
 */
public class AliOSSUtils {

    public static final String FILE_EXT_NAME = "fileextname";

    public static final String FILE_NAME = "filename";

    public static final String HEIGHT = "height";

    public static final String WIDTH = "width";

    public static final String FILE_SIZE = "filesize";

	public static final long PART_SIZE = 1 * 1024 * 1024L;   // 1MB

	private volatile static AliOSSUtils instance = null;

	private OSSClient ossClient = null;

	private AliOSSUtils() {

		// 先判断是否时空
		ossClient = new OSSClient(AliOSSConfigUtils.getInstance()
				.getCommonHost(), AliOSSConfigUtils.getInstance()
				.getAccessKeyId(), AliOSSConfigUtils.getInstance()
				.getAccessKeySecret());
	}


    public static AliOSSUtils getInstance() {
        if (instance == null) {
            synchronized (AliOSSUtils.class) {
                if (instance == null) {
                    instance = new AliOSSUtils();
                }
            }
        }
        return instance;
    }

	/**
	 * 关闭图片服务
	 */
	public void close() {
		if (ossClient != null) {
			ossClient.shutdown();
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param is
	 *            上传文件流
	 * @param fileId
	 *            实际文件名（业务数据保存的文件名，也是阿里实际生成的文件名，通过改名称进行删除操作）
	 * @param fileName
	 *            下载客户端显示的名称
	 * @param contentType
	 *            文件类型
	 * @return
	 */
	public boolean upload(InputStream is, String fileId, String fileName,
			String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();

			meta.setContentType(contentType);
			if(StringUtils.isEmpty(fileName)){
				fileName = fileId;
			}

			String fileSize = String.valueOf(is.available());

            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);

			//用户自定义元信息
			meta.addUserMetadata(FILE_NAME, fileName);
			meta.addUserMetadata(FILE_SIZE, fileSize);
            meta.addUserMetadata(FILE_EXT_NAME, prefix);

			meta.setContentDisposition("attachment;filename=" + fileName + "");
			String bucketName = AliOSSConfigUtils.getInstance().getBucketName();

//			String key = AliOSSConfigUtils.getInstance().getCommonDir() + "/" + realFileName;
            // 先判断是否时空

			ossClient.putObject(bucketName, fileId, is, meta);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param key
	 *            文件名称
	 * @return 文件流
	 */
	public InputStream download(String key) {

		OSSObject obj = ossClient.getObject(AliOSSConfigUtils.getInstance()
				.getBucketName(), key);

		return obj.getObjectContent();
	}

	/**
	 * 删除文件
	 * 
	 * @param key
	 *            文件标识
	 * @return
	 */
	public boolean delete(String key) {
		try {
			ossClient.deleteObject(AliOSSConfigUtils.getInstance()
					.getBucketName(), key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 获取文件上传TOKEN
	 * 
	 * @return
	 */
	public Map<String, String> getToken(String dir) {
		return getUploadToken(dir, false);
	}

	/**
	 * 获取文件上传token
	 * 
	 * @param dir
	 * @param isBigFile
	 * @return
	 */
	private Map<String, String> getUploadToken(String dir, boolean isBigFile) {
		long expireTime = AliOSSConfigUtils.getInstance().getexEireTime();
		long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
		Date expiration = new Date(expireEndTime);
		Map<String, String> respMap = new LinkedHashMap<String, String>();
		try {

			if (StringUtils.isEmpty(dir)) {
				dir = AliOSSConfigUtils.getInstance().getCommonDir();
			}

			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(
					PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith,
					PolicyConditions.COND_KEY, dir);
			String postPolicy = ossClient.generatePostPolicy(expiration,
					policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = ossClient.calculatePostSignature(postPolicy);

			respMap.put("accessid", AliOSSConfigUtils.getInstance()
					.getAccessKeyId());
			respMap.put("policy", encodedPolicy);
			respMap.put("signature", postSignature);
			respMap.put("dir", dir);
			respMap.put("filename",dir + "/" + UUIDGenerator.getUUID());
			if (isBigFile) {
				respMap.put("host", AliOSSConfigUtils.getInstance()
						.getBigFileUploadHost());
			} else {
				respMap.put("host", AliOSSConfigUtils.getInstance()
						.getUploadHost());
			}
			respMap.put("expire", String.valueOf(expireEndTime / 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMap;
	}

	private static AssumeRoleResponse assumeRole() throws ClientException {
		try {
			String REGION_CN_HANGZHOU = AliOSSConfigUtils.getInstance()
					.getOtherParams("region_assume_role");
			String STS_API_VERSION = AliOSSConfigUtils.getInstance()
					.getOtherParams("sts_api_version");
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile(
					REGION_CN_HANGZHOU, AliOSSConfigUtils.getInstance()
							.getAccessKeyId(), AliOSSConfigUtils.getInstance()
							.getAccessKeySecret());
			DefaultAcsClient client = new DefaultAcsClient(profile);
			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(STS_API_VERSION);
			request.setMethod(MethodType.POST);
			request.setProtocol(ProtocolType.HTTPS);
			request.setRoleArn(AliOSSConfigUtils.getInstance().getOtherParams(
					"role_arn"));
			request.setRoleSessionName(AliOSSConfigUtils.getInstance()
					.getOtherParams("role_session_name"));
			// 发起请求，并得到response
			final AssumeRoleResponse response = client.getAcsResponse(request);
			return response;
		} catch (ClientException e) {
			throw e;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (com.aliyuncs.exceptions.ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取app上传token
	 * 
	 * @return
	 */
	public Map<String, String> getAPPToken() {
		AssumeRoleResponse response = AliOSSUtils.assumeRole();
		Map<String, String> map = new HashMap<String, String>();
		if (response != null) {
			map.put("accessKeyId", response.getCredentials().getAccessKeyId());
			map.put("accessKeySecret", response.getCredentials()
					.getAccessKeySecret());
			map.put("securityToken", response.getCredentials()
					.getSecurityToken());
			map.put("expireTime", String.valueOf(AliOSSConfigUtils
					.getInstance().getexEireTime()));
			map.put("bucketName", AliOSSConfigUtils.getInstance()
					.getBucketName());
			map.put("dir", AliOSSConfigUtils.getInstance().getCommonDir());
			map.put("url", AliOSSConfigUtils.getInstance().getImgServiceHost());
			map.put("endpoint", AliOSSConfigUtils.getInstance().getCommonHost());
		}
		return map;
	}

	/**
	 * 获取完整图片对象地址
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String getFilePath(String fileName) {

		if (StringUtils.isEmpty(fileName)) {
			return "";
		}

		if (!fileName.startsWith("http://") && !fileName.startsWith("https://")) {
			fileName = AliOSSConfigUtils.getInstance().getImgServiceHost()
					+ "/" + fileName;
		}

		// add by llj 暂时解决iOS不支持webp格式的问题
		if (fileName.toLowerCase().contains(".webp")
				&& !fileName.toLowerCase().contains("/format,")) {
			return instance.getFormatFilePath(fileName, "jpg", 0);
		}
		// end add

		return fileName;
	}
	
	public static void main(String[] arg){
		//System.out.println(getFileds(new String[]{"commondir/cd5b8bc6245e461186dcdefde2d22f37.png?x-oss-process=image/auto-orient","1"})[0]);
		
		//文件上传测试
		FileInputStream in;
		AliOSSUtils aliOSSUtils = new AliOSSUtils();
		try {
		
			in = new FileInputStream(new File("D:/1.jpg"));			
			if(aliOSSUtils.upload(in,"1.jpg","23.jpg","jpg")){
				System.out.println("文件上传成功");
			}else{
				System.out.println("文件上传失败");
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//获取完整图片对象地址测试
		//System.out.println(aliOSSUtils.getFilePath("1.jpg"));
	
		try {
			
			//下载文件测试
			if (aliOSSUtils.download("1.jpg") != null) {
				System.out.println("下载文件成功");
			}else {
				System.out.println("下载文件失败");
			}
			
			//删除文件测试
			/*if (aliOSSUtils.delete("1.jpg")) {
				System.out.println("删除文件成功");
			}else {
				System.out.println("删除文件失败");
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	private String[] getFileds(String[] picArray){
		List<String> list = new ArrayList<String>();
		for(String tem:picArray){
			if(StringUtils.isNotEmpty(tem) && tem.equals("1")){
				continue;
			}
			if(tem.endsWith("?x-oss-process=image/auto-orient")){
				tem = tem.substring(0,tem.length() - "?x-oss-process=image/auto-orient".length());
			}
			list.add(tem);
		}
		
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 获取完整图片对象地址
	 * 
	 * @param fileNames
	 *            文件名
	 * @return
	 */
	public String getFilePaths(String fileNames) {

		String pictureUrl = null;

		if (StringUtils.isNotEmpty(fileNames)) {
			StringBuffer pic = new StringBuffer();
			String[] picArray = fileNames.split(",");
			picArray = getFileds(picArray);
			for (String picStr : picArray) {
				String tempPic = this.getFilePath(picStr);
				pic.append("," + tempPic);
			}
			pic.delete(0, 1);
			pictureUrl = pic.toString();
		}

		return pictureUrl;
	}

	/**
	 * 指定宽高缩略 --如果有其他缩放需求请参考：
	 * https://help.aliyun.com/document_detail/32218.html?
	 * spm=5176.doc32219.6.490.A72l9F
	 * 
	 * @param fileName
	 *            文件名
	 * @param height
	 *            目标高度
	 * @param width
	 *            目标宽度
	 * @return
	 */
	public String getZoomFilePath(String fileName, int height, int width) {

		if (StringUtils.isEmpty(fileName)) {
			return "";
		}

		if (!fileName.contains("x-oss-process")) {
			fileName = fileName + "?x-oss-process=image/resize";
		} else {
			fileName = fileName + "/resize";
		}

		if (fileName.startsWith("http://") || fileName.startsWith("https://")) {
			fileName = fileName + ",m_lfit,limit_1,w_" + width + ",h_" + height
					+ "";
		} else {
			fileName = AliOSSConfigUtils.getInstance().getImgServiceHost()
					+ "/" + fileName + ",m_lfit,limit_1,w_" + width + ",h_"
					+ height + "";
		}

		// add by llj 暂时解决iOS不支持webp格式的问题
		if (fileName.toLowerCase().contains(".webp")
				&& !fileName.toLowerCase().contains("/format,")) {
			return instance.getFormatFilePath(fileName, "jpg", 0);
		}
		// end add

		return fileName;
	}

	/**
	 * 获取特定输出格式的图片路径
	 * 
	 * @param fileName
	 *            文件名
	 * @param format
	 *            格式，jpg,gif,bmp等等
	 * @param quality
	 *            图片质量百分比，1到100
	 * @return
	 */
	public String getFormatFilePath(String fileName, String format, int quality) {

		if (StringUtils.isEmpty(fileName)) {
			return "";
		}

		if (!fileName.contains("x-oss-process")) {
			fileName = fileName + "?x-oss-process=image";
		}

		if (StringUtils.isNotEmpty(format)) {
			fileName = fileName + "/format," + format;
			if (quality > 0 && quality <= 100) {
				fileName = fileName + "/quality,Q_" + quality;
			}
		}

		if (fileName.startsWith("http://") || fileName.startsWith("https://")) {
			return fileName;
		}

		return AliOSSConfigUtils.getInstance().getImgServiceHost() + "/"
				+ fileName;
	}

	/**
	 * 获取第一张图片
	 * 
	 * @param picId
	 *            图片标识
	 * @return
	 */
	public String getFirstPicUrl(String picId) {

		String picUrl = null;

		if (StringUtils.isNotEmpty(picId)) {
			String[] picArray = picId.split(",");
			if (picArray.length > 0) {
				picUrl = this.getFilePath(picArray[0]);
			}
		}

		return picUrl;
	}

	/**
	 * 获取第一张图片
	 * 
	 * @param picIds
	 *            图片标识
	 * @param height
	 *            缩放后高度
	 * @param width
	 *            缩放后宽度
	 * @return
	 */
	public String getFirstPicUrl(String picIds, int height, int width) {

		String picUrl = null;

		if (StringUtils.isNotEmpty(picIds)) {
			String[] picArray = picIds.split(",");
			if (picArray.length > 0) {
				picUrl = this.getZoomFilePath(picArray[0], height, width);
			}
		}

		return picUrl;
	}

	/**
	 * 获取圆角图片,可以指定圆角的大小 。参考：https://help.aliyun.com/document_detail/44694.html
	 * 
	 * @param fileName
	 *            文件名
	 * @param round
	 *            圆角的半径
	 * @return
	 */
	public String getRoundCornerFilePath(String fileName, int round) {

		if (StringUtils.isEmpty(fileName)) {
			return "";
		}

		if (!fileName.contains("x-oss-process")) {
			fileName = fileName + "?x-oss-process=image/rounded-corners";
		} else {
			fileName = fileName + "/rounded-corners";
		}

		if (fileName.startsWith("http://") || fileName.startsWith("https://")) {
			fileName = fileName + ",r_" + round + "";
		} else {
			fileName = AliOSSConfigUtils.getInstance().getImgServiceHost()
					+ "/" + fileName + ",r_" + round + "";
		}

		// add by llj 暂时解决iOS不支持webp格式的问题
		if (fileName.toLowerCase().contains(".webp")
				&& !fileName.toLowerCase().contains("/format,")) {
			return instance.getFormatFilePath(fileName, "jpg", 0);
		}
		// end add

		return fileName;
	}

	/**
	 * 获取文件全部元信息
	 * @param fileId
	 * @return
	 */
	public ObjectMetadata getObjectMetadata(String fileId){

		ObjectMetadata obj = ossClient.getObjectMetadata(AliOSSConfigUtils.getInstance().getBucketName(),fileId);

		return obj;
	}


    /**
     * 上传文件
     *
     * @param is
     *            文件流
     * @param fileExtName
     *            文件扩展名称
     * @param nameValuePairMap
     *            文件相关的信息（文件名称、大小、需要存储的内容）
     * @return
     * @throws IOException
     */
    public FastResult uploadFile(InputStream is, String fileExtName,
								 Map<String, String> nameValuePairMap) {


        FastResult fastResult = new FastResult();
        //文件名称
        String fileName = UUIDGenerator.getUUID() + "." + fileExtName;

        String fileId = AliOSSConfigUtils.getInstance().getCommonDir() + "/" + fileName;

		try {
			fastResult.setSize(String.valueOf((is.available())));
		} catch (IOException e) {
			e.printStackTrace();
		}

        // OSS上传
        AliOSSUtils.getInstance().upload(is, fileId, fileName, null);

        //拼接返回对象
        fastResult.setFileId(fileId);
        fastResult.setFilePath(getFilePath(fileId));
        fastResult.setFileName(fileName);
        return fastResult;
    }

	/**
	 * 上传文件，并且封装参数
	 * @param fileNames
	 * @param result
	 * @param i
	 * @param file
	 * @param nameValuePairMap
	 */
	private void uploadFileWithInfo(List<String> fileNames, List<FastResult> result, int i, File file, Map<String, String> nameValuePairMap) {

		try {
			FastResult fastResult = uploadFile(new FileInputStream(file),
					getFileNameExt(fileNames.get(i)),
					nameValuePairMap);
			fastResult.setFileName(fileNames.get(i));
			fastResult.setFilePath(getFilePath(fastResult.getFileId()));
			result.add(fastResult);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

    /**
     * 批量上传文件
     *
     * @param files 文件
     * @param fileNames 文件名称
     * @return 每个文件上传的结果
     * @throws IOException
     */
    public  List<FastResult> uploadFile(List<File> files,
                                              List<String> fileNames) {
        List<FastResult> result = new ArrayList<FastResult>();
        if (files != null) {
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                Map<String, String> nameValuePairMap = new HashMap<String, String>();
                nameValuePairMap.put(FILE_NAME, fileNames.get(i));

                uploadFileWithInfo(fileNames, result, i, file, nameValuePairMap);
            }
        }
        return result;
    }

    /**
     * 上传文件
     * @param bytes   文件内容字节数组
     * @param fileExtName   文件扩展名
     * @param nameValuePairMap     文件相关的信息（文件名称、大小、需要存储的内容）
     * @param ifAsynZoom  是否切图
     * @return
     * @throws IOException
     */
    public FastResult uploadFile(byte[] bytes, String fileExtName,
                                        Map<String, String> nameValuePairMap,boolean ifAsynZoom){

        InputStream is = ByteToInputStream.byte2Input(bytes);
        FastResult fastResult = uploadFile(is,fileExtName,nameValuePairMap);

        return fastResult;
    }

	/**
	 * 获取文件名的后缀，不带"."号
	 *
	 * @param fileName
	 * @return
	 */
	private static String getFileNameExt(String fileName){
		String ext = "";
		if (StringUtils.isNotEmpty(fileName.trim())) {
			int index = fileName.lastIndexOf(".") + 1;
			if (index > -1) {
				ext = fileName.substring(index);
			}
		}
		return ext;
	}

}
