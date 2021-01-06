package com.lcy.util.file.fast;

import com.google.gson.reflect.TypeToken;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.file.fast.org.source.MyException;
import com.lcy.util.file.fast.org.source.NameValuePair;
import com.lcy.util.file.fast.org.source.StorageClient1;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 文件上传处理接工具
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-18
 */
@SuppressWarnings("restriction")
public class FastDfsUtils {
	
	/**
	 * 图片文件类型中文名
	 */
	public static final String PICTURE_FILE_TYPE_CN = "图片";

	/**
	 * 图片文件类型中文名
	 */
	public static final String COMMON_FILE_TYPE_CN = "文件";

	public static final String FILE_EXT_NAME = "FILE_EXT_NAME";
	
	public static final String FILE_NAME = "_FILE_NAME";

	public static final String HEIGHT = "_HEIGHT";

	public static final String WIDTH = "_WIDTH";

	public static String HTTP_FAST_SERVER = null;

	/**
	 * 关闭
	 */
	public static void close() {

		ConnectionPool.getConnectionPool().close();

	}

	/**
	 * 上传文件
	 *
	 * @param base64FileDTOs 上传64位字节的文件
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static List<FastResult> uploadFileList(List<Base64FileDTO> base64FileDTOs) {

		// 转换字符串为文件
		List<FastResult> list = new ArrayList<FastResult>();
		FastResult fastResult = null;
		for (Base64FileDTO base64Dto : base64FileDTOs) {
			fastResult = uploadFile(base64Dto);
			list.add(fastResult);
		}
		return list;
	}

	/**
	 * 上传单个文件
	 * @param base64FileDTO 上传64位字节的文件
	 * @return
	 */
	public static FastResult uploadFile(Base64FileDTO base64FileDTO) {

		FastResult fastResult = null;
		Map<String, String> nameValuePairMap = null;
		nameValuePairMap = new HashMap<String, String>();
		nameValuePairMap.put(FILE_NAME, base64FileDTO.getFileName());
		fastResult = uploadFile(Base64Coder.decode(base64FileDTO.getFileStr()),
				FastDfsUtils.getFileNameExt(base64FileDTO.getFileName()), nameValuePairMap);
		fastResult.setFileName(base64FileDTO.getFileName());

		return fastResult;
	}
	

	/**
	 * 获取文件名的后缀，不带"."号
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameExt(String fileName){
		String ext = "";
		if (StringUtils.isNotEmpty(fileName.trim())) {
			int index = fileName.lastIndexOf(".") + 1;
			if (index > -1) {
				ext = fileName.substring(index);
			}
		}
		return ext;
	}
	
	/**
	 * 通过文件id获取完整的文件路径
	 * @param fileId	文件id
	 * @return
	 */
	public static String getFilePathById(String fileId) {
		try{
			if(StringUtils.isNotEmpty(fileId)){
//				return FastDfsUtils.getFastUrl() + FastDfsUtils.getFilePathByFileId(fileId);
				if(!fileId.startsWith("http")){
					return FastDfsUtils.getFastUrl() + FastDfsUtils.getFilePathByFileId(fileId);
				}else{
					return fileId;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 通过文件id获取指定规格的完整的文件路径
	 * @param fileId	文件id
	 * @return
	 */
	public static String getSubFilePathById(String fileId,String subName) {
		int lastIndex = fileId.lastIndexOf(".");
		fileId = fileId.substring(0, lastIndex)+subName+fileId.substring(lastIndex);
		if(!fileId.startsWith("http")){
			return FastDfsUtils.getFastUrl() + FastDfsUtils.getFilePathByFileId(fileId);
		}else{
			return  fileId;
		}
	}

	/**
	 * 验证文件大小及文件格式
	 * 
	 * @param files
	 *            文件
	 * @param fileNames
	 *            文件名
	 * @param fileExts
	 *            支持的文件扩展名
	 * @param maxFileSize
	 *            文件最大尺寸
	 * @param isContain
	 *            是否是包含（true：包含，false：排除）
	 * @param fileType
	 *            检验类型，目前就两个：图片，文件--用来异常抛出，前台提示
	 * @return
	 */
	public static boolean validateFile(List<File> files,
			List<String> fileNames, long maxFileSize, String fileExts,
			boolean isContain, String fileType) throws MyException {

		boolean flag = false;
		for (int i = 0; i < files.size(); i++) {

			flag = validateFile(files.get(i), fileNames.get(i), maxFileSize,
					fileExts, isContain, fileType);
			if (!flag) {
				return flag;
			}
		}
		return flag;
	}

	/**
	 * 验证文件大小及文件格式
	 * 
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名
	 * @param maxFileSize
	 *            文件最大尺寸
	 * @param fileExts
	 *            支持的文件扩展名
	 * @param fileType
	 *            文件类型，主要用于错误提示显示用，一般为文件、图片
	 * @return
	 */
	public static boolean validateFile(File file, String fileName,
			long maxFileSize, String fileExts, boolean isContain,
			String fileType) throws MyException {

		if (file.length() > maxFileSize) {
			int max = (int) (maxFileSize / (1024 * 1024));

			// 请选择小于{0}M的{1}！
			throw new MyException("请选择小于" + max + "M的" + fileType);

			// TODO 抛异常
//			throw new CcipAppException(ExceptionType.TIP, "90023", max + "",fileType);
		}

		String fileExt = FastDfsUtils.getFileNameExt(fileName);
		if (StringUtils.isNotEmpty(fileExt)) {
			fileExt = fileExt.toLowerCase();
		}
		if ((isContain && !fileExts.contains(fileExt))
				|| (!isContain && fileExts.contains(fileExt))) {

			// 上传的{0}格式不符合要求！
			throw new MyException("上传的" + fileType + "格式不符合要求！");
			// TODO 抛异常
//			throw new CcipAppException(ExceptionType.TIP, "90035", fileType);
		}
		return true;
	}
	
	/**
	 * 验证文件大小及文件格式
	 * 
	 * @param fileDTOs base64格式图片格式和名称
	 *            文件
	 * @param maxFileSize
	 *            文件最大尺寸
	 * @param fileExts
	 *            支持的文件扩展名
	 * @param fileType
	 *            文件类型，主要用于错误提示显示用，一般为文件、图片
	 * @return
	 */
	public static boolean validateFile(List<Base64FileDTO> fileDTOs,
			long maxFileSize, String fileExts, boolean isContain,
			String fileType) throws MyException {
		String fileExtName = null;
		if(fileDTOs!=null&&fileDTOs.size()>0){
			for (Base64FileDTO base64FileDTO : fileDTOs) {
				fileExtName = FastDfsUtils.getFileNameExt(base64FileDTO.getFileName());
				//图片格式错误验证
				if(!fileExts.contains(fileExtName.toLowerCase())){

					// 上传的{0}格式不符合要求！
					throw new MyException("上传的" + fileType + "格式不符合要求！");
					// TODO 抛异常
//					throw new CcipAppException(ExceptionType.TIP, "90035", fileType);
				}
				//图片大小验证
				BASE64Decoder decoder = new BASE64Decoder(); 
				try {
					byte[] b = decoder.decodeBuffer(base64FileDTO.getFileStr());
					if(b.length>maxFileSize){
						int max = (int) (maxFileSize / (1024 * 1024));

						// 请选择小于{0}M的{1}！
						throw new MyException("请选择小于" + max + "M的" + fileType);
						// TODO 抛异常
//						throw new CcipAppException(ExceptionType.TIP, "90023", max + "",fileType);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		 return false;
	}

	/**
	 * 获取客户端
	 * 
	 * @return
	 */
	private static StorageClient1 getClient() {
		return ConnectionPool.getConnectionPool().checkout(10);

	}

	/**
	 * 获取文件系统的url基路径
	 * 
	 * @return
	 */
	public static String getFastUrl() {
		if (null != HTTP_FAST_SERVER) {
			return HTTP_FAST_SERVER;
		}

		synchronized (FastDfsUtils.class) {
			if (null == HTTP_FAST_SERVER) {
				init();
			}
		}

		return HTTP_FAST_SERVER;
	}

	private static void init() {
		ConnectionPool.getConnectionPool();
	}

	/**
	 * 子文件上传
	 * @param masterFileId	附文件id
	 * @param prefixName	子文件的前缀，该字段会加载附文件id的后面形成子文件的id，一般是200x200格式
	 * @param bytes			子文件的字节码
	 * @param subFileExtName 子文件的后缀名
	 * @param nameValuePairMap 子文件的其他信息
	 * @return	子文件id
	 */
	public static String uploadSubFile(String masterFileId, String prefixName,byte[] bytes,String subFileExtName,
			Map<String, String> nameValuePairMap) {

		NameValuePair[] metaList = null;
		
		if (nameValuePairMap != null && nameValuePairMap.size() > 0) {
			metaList = new NameValuePair[nameValuePairMap.size() + 1];
			int i = 1;
			metaList[0] = new NameValuePair(FILE_EXT_NAME, subFileExtName);
			for (Entry<String, String> entry : nameValuePairMap.entrySet()) {
				metaList[i++] = new NameValuePair(entry.getKey(),
						entry.getValue());
			}
		} else {
			metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair(FILE_EXT_NAME, subFileExtName);
		}
		StorageClient1 storageClient = getClient();
		String  result = "";

		try {
			result = storageClient.upload_file1(masterFileId, prefixName, bytes, subFileExtName,metaList);
			ConnectionPool.getConnectionPool().checkin(storageClient);
		} catch (IOException e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		} catch (MyException e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		} catch (NullPointerException e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		}catch (Exception e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
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
	 * @throws MyException
	 * @throws IOException
	 */
	public static FastResult uploadFile(byte[] bytes, String fileExtName,
			Map<String, String> nameValuePairMap,boolean ifAsynZoom){
		FastResult fastResult = new FastResult();

		NameValuePair[] metaList  = new NameValuePair[0];
		try {
			metaList = FastDfsUtils.getMetadata(nameValuePairMap,fileExtName);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (MyException e1) {
			e1.printStackTrace();
		}
		 
		StorageClient1 storageClient = getClient();
		String[] fileIds = null;

		try {
			fileIds = storageClient.upload_file(bytes, fileExtName, metaList);
			fastResult.setGroupName(fileIds[0]);
			fastResult.setFilePath(fileIds[0] + "/" + fileIds[1]);
			fastResult.setFileId(fileIds[0] + "/" + fileIds[1]);
			//图片压缩
			if(nameValuePairMap!=null && nameValuePairMap.get(FILE_NAME)!=null){
				fastResult.setFileName(nameValuePairMap.get(FILE_NAME));
			}
			//文件大小统一kB
			fastResult.setSize(getSizeUnit(bytes.length));
			ConnectionPool.getConnectionPool().checkin(storageClient);
			fastResult.setFlag(true);
		} catch (IOException e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		} catch (MyException e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		} catch (NullPointerException e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		}catch (Exception e) {
			ConnectionPool.getConnectionPool().drop(storageClient);
			e.printStackTrace();
		}finally {
			bytes = null;
		}
		if(ifAsynZoom){
			PictureUtils.asynZoom(fastResult);
		}
		return fastResult;
	}
	
	/**
	 * 上传文件
	 * @param bytes   文件内容字节数组
	 * @param fileExtName   文件扩展名
	 * @param nameValuePairMap     文件相关的信息（文件名称、大小、需要存储的内容）
s	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	public static FastResult uploadFile(byte[] bytes, String fileExtName,
			Map<String, String> nameValuePairMap) {
		 return uploadFile(bytes,fileExtName,nameValuePairMap,true);
	}
	
	
	/**
	 * 获取文件大小
	 * @param length
	 * @return
	 */
	private static  String getSizeUnit(long length){
		Double size = null;
		NumberFormat ddf1=NumberFormat.getNumberInstance(); 
		ddf1.setMaximumFractionDigits(2);
		if(length>1024*1024){ //M
			size = length/(1024*1024*1.0);
			return ddf1.format(size)+"M";
		}else if(length>1024){//kB
			size = length/(1024*1.0);
			return ddf1.format(size)+"KB";
		}else{//b
			return size+"B";
		}
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
	 * @throws MyException
	 */
	public static FastResult uploadFile(InputStream is, String fileExtName,
			Map<String, String> nameValuePairMap) {

		FastResult fastResult = new FastResult();
		byte[] bytes;
		try {
			bytes = ByteToInputStream.input2byte(is);
			fastResult = uploadFile(bytes, fileExtName, nameValuePairMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fastResult;
	}

	/**
	 * 上传文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileExtName
	 *            文件扩展名
	 * @param nameValuePairMap
	 *            文件相关的信息（文件名称、大小、需要存储的内容）
	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	public static FastResult uploadFile(String filePath, String fileExtName,
			Map<String, String> nameValuePairMap) {

		NameValuePair[] metaList = null;

		if (nameValuePairMap != null && nameValuePairMap.size() > 0) {
			metaList = new NameValuePair[nameValuePairMap.size() + 1];
		} else {
			metaList = new NameValuePair[1];			
		}
		metaList[0] = new NameValuePair(FILE_EXT_NAME, fileExtName);

		if(nameValuePairMap!=null && nameValuePairMap.size() > 0 ){
			int i = 1;
			for (Entry<String, String> entry : nameValuePairMap.entrySet()) {
				metaList[i++] = new NameValuePair(entry.getKey(),
						entry.getValue());
			}
		}

		
		String[] fileIds = null;
		StorageClient1 storageClient1 = getClient();
		try {
			fileIds = storageClient1.upload_file(filePath, fileExtName,
					metaList);
			ConnectionPool.getConnectionPool().checkin(storageClient1);
		} catch (IOException e) {
			e.printStackTrace();
			ConnectionPool.getConnectionPool().drop(storageClient1);
		} catch (MyException e) {
			e.printStackTrace();
			ConnectionPool.getConnectionPool().drop(storageClient1);
		}catch (NullPointerException e) {
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		}catch (Exception e) {
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		}

		FastResult fastResult = new FastResult();
		fastResult.setFileName(fileExtName);
		if (fileIds != null) {
			fastResult.setFlag(true);
			fastResult.setGroupName(fileIds[0]);
			fastResult.setFilePath(fileIds[1]);
			fastResult.setFileId(fileIds[0] + "/" + fileIds[1]);
		} else {
			fastResult.setFlag(false);
		}
		PictureUtils.asynZoom(fastResult);

		return fastResult;
	}

	/**
	 * 批量上传文件
	 * 
	 * @param files 文件
	 * @param fileNames 文件名称
	 * @return 每个文件上传的结果
	 * @throws MyException
	 * @throws IOException
	 */
	public static List<FastResult> uploadFile(List<File> files,
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
	 * 批量上传图片
	 *
	 * @param files 文件
	 * @param fileNames 文件名称
	 * @return 每个文件上传的结果
	 * @throws MyException
	 * @throws IOException
	 */
	public static List<FastResult> uploadPic(List<File> files,
											  List<String> fileNames, List<Integer> heights, List<Integer> widths) {
		List<FastResult> result = new ArrayList<FastResult>();
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				File file = files.get(i);
				Map<String, String> nameValuePairMap = new HashMap<String, String>();
				nameValuePairMap.put(FILE_NAME, fileNames.get(i));
				nameValuePairMap.put(HEIGHT, heights.get(i).toString());
				nameValuePairMap.put(WIDTH,widths.get(i).toString());

				uploadFileWithInfo(fileNames, result, i, file, nameValuePairMap);
			}
		}
		return result;
	}

	/**
	 * 上传文件，并且封装参数
	 * @param fileNames
	 * @param result
	 * @param i
	 * @param file
	 * @param nameValuePairMap
	 */
	private static void uploadFileWithInfo(List<String> fileNames, List<FastResult> result, int i, File file, Map<String, String> nameValuePairMap) {
		try {
			FastResult fastResult = uploadFile(new FileInputStream(file),
					FastDfsUtils.getFileNameExt(fileNames.get(i)),
					nameValuePairMap);
			fastResult.setFileName(fileNames.get(i));
			fastResult.setFilePath(FastDfsUtils.getFilePathById(fastResult.getFileId()));
			result.add(fastResult);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param groupName
	 * @param remoteFilename
	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	private static LoadFile downFile(String groupName, String remoteFilename) {

		StorageClient1 storageClient1 = getClient();

		LoadFile loadFile = new LoadFile();

		try {

			byte[] result = null;

			result = storageClient1.download_file(groupName, remoteFilename);
			loadFile.setBytes(result);

			NameValuePair[] nameValuePairs = storageClient1.get_metadata(
					groupName, remoteFilename);

			Map<String, String> map = new HashMap<String, String>();
			if (nameValuePairs != null && nameValuePairs.length > 0) {
				for (NameValuePair pair : nameValuePairs) {
					map.put(pair.getName(), pair.getValue());
				}
			}

			loadFile.setNameValuePairMap(map);
			loadFile.setFlag(true);
			ConnectionPool.getConnectionPool().checkin(storageClient1);

		} catch (IOException e) {
			loadFile.setFlag(false);
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		} catch (MyException e) {
			loadFile.setFlag(false);
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		} catch (NullPointerException e) {
			loadFile.setFlag(false);
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		}catch (Exception e) {
			loadFile.setFlag(false);
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		}

		return loadFile;
	}

	/**
	 * 下载文件
	 * 
	 * @param fileId
	 *            文件组+文件路径
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static LoadFile downFile(String fileId) {

		int index = fileId.indexOf("/");
		String groupName = fileId.substring(0, index);
		String filePath = fileId.substring(index + 1);

		return downFile(groupName, filePath);

	}

	/**
	 * 删除文件
	 * 
	 * @param fileId
	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	public static boolean deleteFile(String fileId) throws IOException,
			MyException {
		int index = fileId.indexOf("/");
		String groupName = fileId.substring(0, index);
		String remoteFilename = fileId.substring(index + 1);
		return deleteFile(groupName, remoteFilename);
	}

	/**
	 * 删除文件
	 * 
	 * @param groupName
	 * @param remoteFilename
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	public static boolean deleteFile(String groupName, String remoteFilename) {
		int i = 1;
		StorageClient1 storageClient1 = getClient();
		try {
			i = storageClient1.delete_file(groupName, remoteFilename);
			ConnectionPool.getConnectionPool().checkin(storageClient1);
		} catch (IOException e) {
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		} catch (MyException e) {
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		} catch (NullPointerException e) {
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		}catch (Exception e) {
			ConnectionPool.getConnectionPool().drop(storageClient1);
			e.printStackTrace();
		}

		return i == 0;
	}

	/**
	 * 获取存储的内容
	 * 
	 * @param nameValuePairMap
	 * @param fileExtName
	 * @return
	 * @throws IOException
	 * @throws MyException
	 */
	private static NameValuePair[] getMetadata(Map<String, String> nameValuePairMap,
			String fileExtName) throws IOException, MyException {

		NameValuePair[] metaList = null;

		if (nameValuePairMap != null && nameValuePairMap.size() > 0) {
			metaList = new NameValuePair[nameValuePairMap.size() + 1];
			int i = 1;
			metaList[0] = new NameValuePair(FILE_EXT_NAME, fileExtName);
			for (Entry<String, String> entry : nameValuePairMap.entrySet()) {
				metaList[i++] = new NameValuePair(entry.getKey(),
						entry.getValue());
			}
		} else {
			metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair(FILE_EXT_NAME, fileExtName);
		}
		
		return metaList;
	}
	
	/**
	 * 通过id获取路径
	 * 
	 * @param fileId
	 *            文件id
	 * @return 文件路径
	 */
	private static String getFilePathByFileId(String fileId) {
		return fileId;
	}

	/**
	 * 通过多个用逗号隔开的文件id获取文件列表
	 * 
	 * @param filedIds	文件id
	 * @return
	 */
	public static List<FastResult> getFilesByIds(String filedIds) {
		List<FastResult> list = new ArrayList<FastResult>();
		if (StringUtils.isEmpty(filedIds)||"[]".equals(filedIds)) {
			return list;
		}
		String[] filedArray = filedIds.split(",");
		for (int i = 0; i < filedArray.length; i++) {
			FastResult result = new FastResult();
			result.setFileId(filedArray[i]);
			result.setFilePath(FastDfsUtils.getRealUrlContent(getFilePathByFileId(filedArray[i])));
			list.add(result);
		}
		return list;
	}
	
	/**
	 * 根据多文件的json字符串获取文件路径，每个路径按,隔开
	 * 
	 * @param filedIds
	 *            多文件的json字符串
	 * @return 文件路径，每个路径按,隔开
	 * @author lliangjian@linewell.com
	 * @since 2016年3月7日
	 */
	public static String getFilePathesByIds(String filedIds) {

		StringBuilder filePathesBuilder = new StringBuilder();

		if (StringUtils.isNotEmpty(filedIds)) {
			List<FastResult> resultList = getFilesByIds(filedIds);
			if (resultList != null) {
				for (FastResult result : resultList) {
					if (filePathesBuilder.length() > 0) {
						// TODO ,常量
//						filePathesBuilder.append(StringUtil.STRINGSPLIT);
						filePathesBuilder.append(",");
					}
					filePathesBuilder.append(result.getFilePath());
				}
			}
		}

		return filePathesBuilder.toString();
	}
	
	/**
	 * 设置原图的图片大小
	 * @param filedIds			父文件ID列表，用逗号隔开
	 * @param originalSizeType	原图的大小
	 * @param listSizeType		列表图的大小
	 * @return  List<FastResult> 
	 */
	public static  List<FastResult>  getPicByFilesId(String filedIds,
													 CommonPictureSizeType originalSizeType, CommonPictureSizeType listSizeType) {
		if (StringUtils.isEmpty(filedIds)||"[]".equals(filedIds)) {
			return null;
		}
		List<FastResult> list = new ArrayList<FastResult>();
		if (StringUtils.isEmpty(filedIds)||"[]".equals(filedIds)) {
			return list;
		}
		String[] filedArray = filedIds.split(",");
		for (int i = 0; i < filedArray.length; i++) {
			FastResult result = new FastResult();
			result.setFilePath(FastDfsUtils.getSubFilePath(filedArray[i], listSizeType));
			result.setFileId(FastDfsUtils.getSubFilePath(filedArray[i],
					originalSizeType));
			list.add(result);
		}
		return list;
	}
	
	/**
	 * 通过多个用逗号隔开的文件id获取文件 指定规格图片列表
	 * 
	 * @param filedIds	父文件ID列表，用逗号隔开
	 * @return	指定规格文件列表
	 */
	public static List<FastResult> getSubFilesByIds(String filedIds,CommonPictureSizeType sizeType) {
		List<FastResult> list = new ArrayList<FastResult>();
		if (StringUtils.isEmpty(filedIds)||"[]".equals(filedIds)) {
			return list;
		}
		String[] filedArray = filedIds.split(",");
		for (int i = 0; i < filedArray.length; i++) {
			FastResult result = new FastResult();
			result.setFilePath(FastDfsUtils.getSubFilePath(filedArray[i], sizeType));
			result.setFileId(FastDfsUtils.getSubFilePath(filedArray[i],
					CommonPictureSizeType.SIZE_4_3));
			list.add(result);
		}
		return list;
	}
	
/*	*//**
	 * App前端，通过多个用逗号隔开的文件id获取文件 指定规格图片列表
	 * 
	 * 默认原图为 SIZE_720_230
	 * 
//	 * @param filedIds	父文件ID列表，用逗号隔开
	 * @return	指定规格文件列表
	 *//*
	public static List<FastResult> getAppSubFilesByIds(String filedIds,CommonPictureSizeType sizeType) {
		List<FastResult> list = new ArrayList<FastResult>();
		if (StringUtils.isEmpty(filedIds)||"[]".equals(filedIds)) {
			return list;
		}
		String[] filedArray = filedIds.split(",");
		for (int i = 0; i < filedArray.length; i++) {
			FastResult result = new FastResult();
			result.setFilePath(FastDfsUtils.getSubFilePath(filedArray[i], sizeType));
			result.setFileId(FastDfsUtils.getSubFilePath(filedArray[i],
					CommonPictureSizeType.SIZE_720_230));
			list.add(result);
		}
		return list;
	}*/
	
	public static void main(String[] args) {
		
		//上传文件测试(文件流)
//		FileInputStream in;
//		try {
//			//in = new FileInputStream(new File("C:\\Users\\tujianqun\\Pictures\\apk.jpg"));
//			in = new FileInputStream(new File("E:/1.mp4"));
//			FastResult result = FastDfsUtils.uploadFile(ByteToInputStream.input2byte(in), "mp4", null, false);
//			System.out.println("上传文件测试(文件流)");
//			System.out.println(result.getFileId());
//			System.out.println(result.getFilePath());
//			System.out.println(FastDfsUtils.getFilePathById(result.getFileId()));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		//上传文件测试(文件路径)
//		try {
//			FastResult result = FastDfsUtils.uploadFile("D:/1.txt", "txt", null);
//			System.out.println("上传文件测试(文件路径)");
//			System.out.println(result.getFileId());
//			System.out.println(result.getFilePath());
//			System.out.println(FastDfsUtils.getFilePathById(result.getFileId()));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
		//文件下载测试(通过fileId 文件组+文件路径)
		FileOutputStream os = null;
		try {
			LoadFile loadFile = FastDfsUtils.downFile("group1/M00/10/C4/wKjLQ1wy14CAELATAA1OqteEqSk749.mp4");
			if (null != loadFile) {
				System.out.println("文件下载成功");
				os = new FileOutputStream(new File("E://2.mp4"));
				os.write(loadFile.getBytes());
				os.flush();
			}else {
				System.out.println("文件下载失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			while (os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//
//		//文件下载测试(通过groupName和remoteFilename)
//		try {
//			if (null != FastDfsUtils.downFile("group1","M00/05/B0/wKjLQ1tRXEeAACPiAAAABIwNizU295.txt")) {
//				System.out.println("文件下载成功");
//			}else {
//				System.out.println("文件下载失败");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}
	

	/**
	 * 返回真实url地址
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getRealUrlContent(String filePath) {
		return FastDfsUtils.getFastUrl() + filePath;
	}

	/**
	 * 去掉前缀
	 * 
	 * @param filePath 文件
	 * @return
	 */
	public static String getTagedUrlContent(String filePath) {
		if (filePath.startsWith(FastDfsUtils.getFastUrl())) {
			return filePath.substring(FastDfsUtils.getFastUrl().length());
		}
		return filePath;
	}

	/**
	 * 根据父亲id获取子文件id
	 * @param parentFileId 文件id
	 * @param subName		      子文件后缀
	 * @return
	 */
	public static String getSubFileId(String parentFileId, String subName) {
		if(StringUtils.isEmpty(parentFileId)){
			return null;
		}
		int index = parentFileId.lastIndexOf(".");
		String fileId = parentFileId.substring(0,index)+subName+parentFileId.substring(index, parentFileId.length());
		return fileId;
	}
	
	/**
	 * 根据父亲id获取子文件id
	 * @param parentFileId 文件id
	 * @param sizeType	   尺寸图
	 * @return
	 */
	public static String getSubFilePath(String parentFileId, CommonPictureSizeType sizeType) {
		return FastDfsUtils.getFilePathById(FastDfsUtils.getSubFileId(parentFileId,sizeType.getSizeName()));
	}
	
	/**
	 * 重新上传文件
	 * @param fileId 原文件标识
	 * @return 新文件标识
	 */
	public static FastResult reUploadFile(String fileId) {
		
		// 下载文件
		LoadFile file = downFile(fileId);

		if(file == null) {
			return null;
		}

		// 上传文件，不异步切图
		FastResult result = uploadFile(file.getBytes(), file.getNameValuePairMap().get(FILE_EXT_NAME), file.getNameValuePairMap());
		
		return result;
	}

	/**
	 * 重新上传文件列表
	 * @param fileAryId 原文件列表标识
	 * @return 新文件标识
	 */
	public static List<FastResult> reUploadFileList(String fileAryId) {
		
		if(StringUtils.isEmpty(fileAryId)) {
			return null;
		}
		
		List<FastResult> resultList = GsonUtils.jsonToBean(fileAryId, new TypeToken<List<FastResult>>() {}.getType());
		if(resultList == null) {
			return null;
		}
		
		List<FastResult> newResultList = new ArrayList<FastResult>();
		for (FastResult fastResult : resultList) {
			newResultList.add(reUploadFile(fastResult.getFileId()));
		}
		
		return newResultList;
	}
 
}
