package com.lcy.util.file.fast;

import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.fast.org.source.MyException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片处理类
 * 
 * @author cjianyan@linewell.com
 * @since 2016-1-19
 */
public class PictureUtils {

	private static Logger logger = LoggerFactory.getLogger(PictureUtils.class);
	/**
	 * 图片最大5M
	 */
	public static final long PICTURE_MAX_SIZE = 5 * 1024 * 1024;
	/**
	 * 稿件最大2m
	 */
	public static final long CONTRIBUTION_FILE_MAX_SIZE = 2 * 1024 * 1024;

	// 任务附件上传文件最大10M
	public static final long TASK_FILE_LENGTH = 10 * 1024 * 1024;

	// 稿件附件上传文件最大70M
	public static final long CONTRIBUTION_FILE_LENGTH = 70 * 1024 * 1024;


	// 图片支持的格式
	public static final String PICTURE_EXTS = ".jpg,.png,.bmp,.jpeg,.svg";

	//文件支持的格式
	public static final String FILE_EXTS = ".xls,.xlsx,.pptx,.pdf,.docx,.doc,.ppt,.apk,.txt,.keystore,.mobileprovision,.p12,.zip,.pem,.csv,.jks";
	
	public static final String PICTURE_SIZE_200="200_200";
	
	public static final String PICTURE_SIZE_80="80_80";
	
	public static final String PICTURE_SIZE_48="48_48";
	
	
	// 作品原件附件上传文件最大50M
	public static final long ORIGINAL_FILE_LENGTH = 50 * 1024 * 1024;

	// 文件排除格式
	public static final String FILE_EXCEPT_EXTS = ".com,.exe,.scr,.dll,.ocx,.sys,.bat,.vbs,.cmd,.html,.htm,.log,.reg,.ing,.apk,.ipa,.ini,.ico,.inf";

	// 源文件排除格式
	public static final String ORIGINAL_FILE_EXCEPT_EXTS = ".com,.scr,.dll,.ocx,.sys,.bat,.vbs,.cmd,.html,.htm,.log,.reg,.ing,.ini,.ico,.inf";

	/**
	 * 等比例压缩的文件名前缀
	 */
	public static final String ZOOM_WIDTH_CONSTRAIN="_ORIGINAL_";
	
	//线程池用来异步处理图片压缩
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);  

	/**
	 * 剪切并上传剪切的图片
	 * 
	 * @param fileId
	 *            图片的地址
	 * @param customPicSize
	 *            剪切的大小
	 * @return
	 * @throws Exception 
	 */
	public static FastResult cutCustomAndUploadFile(String fileId,
													CustomPicSize customPicSize) throws Exception {

		FastResult fastResult = new FastResult();

		LoadFile loadFile = FastDfsUtils.downFile(fileId);

		InputStream is = ByteToInputStream.byte2Input(loadFile.getBytes());
		String ext = loadFile.getNameValuePairMap().get(
				FastDfsUtils.FILE_EXT_NAME);

		File tempFile = File.createTempFile(UUIDGenerator.getUUID(), ext);
		// 存放剪切完的图片输出流
		OutputStream outputStream = new FileOutputStream(tempFile);
		ImageUtils.cut(is, customPicSize.getX(), customPicSize.getY(),
				customPicSize.getWidth(), customPicSize.getHeight(),
				outputStream);
		fastResult = FastDfsUtils.uploadFile(tempFile.getAbsolutePath(), ext,
				null);
		fastResult.setFilePath(FastDfsUtils.getFilePathById(fastResult.getFileId()));
		tempFile.delete();

		return fastResult;
	}
	
	
	public static void main(String[] args) {

		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());
		System.out.println(UUIDGenerator.getUUID());



//		System.out.println(PictureUtils.PICTURE_EXTS.contains("JPEG".toLowerCase()));
//		//String filePath = "C:\\Users\\tujianqun\\Pictures\\app.jpg";
//
//		String filePath = "D:/1.jpg";
//
//		/**
//		FastResult result = new FastResult();
//			result.setFileId("group1/M00/00/27/wKjLulbFj9GAX796AAFvr_jfw2o157.jpg");
//			result.setFileName("jpg");
//			PictureUtils.asynZoom(result);
//			PictureUtils.zoom("group1/M00/00/27/wKjLulbFj9GAX796AAFvr_jfw2o157.jpg", CommonPictureSizeType.getPicSize());
//		 */
//
//		InputStream is;
//		try {
//			is = new FileInputStream(new File(filePath));
////			BufferedImage src = javax.imageio.ImageIO.read(is);
////			int width = src.getWidth(null); // 得到宽
////			int height = src.getHeight(null); //得到高
////			FastResult result = FastDfsUtils.uploadFile(is, "jpg", null);
////			System.out.println(result.getFileId());
//			CustomPicSize cut = new CustomPicSize(140,150, 549, 365);
//
//			FastResult cutFile = cutCustomAndUploadFile("group1/M00/0A/48/wKjLQ1uDzveALpjcAAGNZrpq1vI349.jpg", cut);
//
//			System.out.println("剪切:" + cutFile.getFileId());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}
	
	/**
	 * 同步压缩图片
	 * @param sourceFileId	待雅俗图片资源
	 * @param picSizeDtos	压缩规格
	 * @return
	 */
	public static List<FastResult> zoom(String sourceFileId,List<PicSize> picSizeDtos){
		
		FastResult result = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		List<FastResult> resultList = new ArrayList<FastResult>();
 
		LoadFile loadFile = FastDfsUtils.downFile(sourceFileId);
		File tempFile = null;
		//下载文件并写入到流
		for (PicSize picSizeDTO : picSizeDtos) {
		  try{
			inputStream = new ByteArrayInputStream(loadFile.getBytes());
			String ext = loadFile.getNameValuePairMap().get(FastDfsUtils.FILE_EXT_NAME);
			result = new FastResult();
			// 存放剪切完的图片输出流
			tempFile = File.createTempFile(UUIDGenerator.getUUID(), ext);
			outputStream = new FileOutputStream(tempFile);
			int width = picSizeDTO.getWidth();
			int height = picSizeDTO.getHeight();
			
			// 如果高度为0，则按默认值
			if(picSizeDTO.getHeight() == 0){
				height = ImageUtils.DEFAULT_VALUE;
				
			}
			if(picSizeDTO.getWidth() == 0){
				width = ImageUtils.DEFAULT_VALUE;
			}
			 
			if(picSizeDTO.getName().startsWith(PictureUtils.ZOOM_WIDTH_CONSTRAIN)
					||picSizeDTO.getName().equals(CommonPictureSizeType.SIZE_720_230.getSizeName())){
				ImageUtils.zoomWidthConstrain(inputStream, width, height, outputStream);
			}else{
				ImageUtils.zoom(inputStream, width, height, outputStream);
			}
			
			//上传子文件
			inputStream = new FileInputStream(tempFile);
			String fileId = FastDfsUtils.uploadSubFile(sourceFileId, picSizeDTO.getName(), 
					PictureUtils.getByteFormInputStream(inputStream), ext, null);
			
			//如果fileId==null,说明重复提交
			if(fileId==null){
				fileId = FastDfsUtils.getSubFileId(sourceFileId, picSizeDTO.getName());
			}
			result.setFileId(fileId);
			result.setFilePath(FastDfsUtils.getFilePathById(fileId));
			// 关闭流
			resultList.add(result);
			
		  }catch(IOException e){
			  logger.error("文件读写失败！");
		  }finally{
				try{
					if(outputStream!=null){
						outputStream.close();
					}
					if(inputStream!=null){
						inputStream.close();
					}
					//及时删除临时文件
					if(tempFile!=null&&tempFile.exists()&&tempFile.isFile()){
						tempFile.delete();
					}
				}catch(IOException e){
					 
				}
			}
		}
		return resultList;
	}
	
	
	/**
	 * 异步压缩图片上传
	 * @param sourceFileId	待雅俗图片资源
	 * @param picSizeDtos	压缩规格
	 * @return
	 */
	public static void asynZoom(String  sourceFileId,List<PicSize> picSizeDtos){
		fixedThreadPool.execute(new ZoomPictureThread(sourceFileId,picSizeDtos));
	}
	
	/**
	 * 异步压缩图片上传
	 * @param sourceFileId	待雅俗图片资源
	 * @return
	 */
	public static void asynZoom(String  sourceFileId){
		List<PicSize> picSizeDtos = CommonPictureSizeType.getPicSize();
		fixedThreadPool.execute(new ZoomPictureThread(sourceFileId,picSizeDtos));
	}
	
	/**
	 * 检查是否是图片，将图片异步压缩上传
	 * @param resultList	结果集合
	 * @return
	 */
	public static void asynZoom(List<FastResult>  resultList){
		List<PicSize> picSizeDtos = CommonPictureSizeType.getPicSize();
		for (FastResult result : resultList) {
			PictureUtils.asynZoom(result,picSizeDtos);
		}
	}
	
	/**
	 * 单个文件压缩
	 * @param result		上传结果对象
	 * @param picSizeDtos	压缩尺寸
	 */
	private static void asynZoom(FastResult  result,List<PicSize> picSizeDtos){
		String fileExtName = result.getFileName();
		if(PictureUtils.PICTURE_EXTS.contains(fileExtName.toLowerCase())){
			fixedThreadPool.execute(new ZoomPictureThread(result.getFileId(),picSizeDtos));
		}
	}
	
	/**
	 * 单个文件压缩
	 * @param result		上传结果对象
	 */
	public static void asynZoom(FastResult  result){
		List<PicSize> picSizeDtos = CommonPictureSizeType.getPicSize();
		String fileExtName = result.getFileName();
		if(PictureUtils.PICTURE_EXTS.contains(fileExtName.toLowerCase())){
			fixedThreadPool.execute(new ZoomPictureThread(result.getFileId(),picSizeDtos));
		}
	}
	
	
	 
 
	/**
	 * 剪切并上传剪切的图片
	 * @param fileId	文件id
	 * @param x			开始x坐标
	 * @param y			开始y坐标
	 * @param picSizeList 截取规格
	 * @return
	 */
	public static List<FastResult> cutAndUploadFile(String fileId,int x,int y,
			List<PicSize> picSizeList){

		List<FastResult> list = new ArrayList<FastResult>();

		LoadFile loadFile = null;
		loadFile = FastDfsUtils.downFile(fileId);
		InputStream is = null;
		String ext = loadFile.getNameValuePairMap().get(FastDfsUtils.FILE_EXT_NAME);
		FastResult fastResult = null;
		try{
			//原图先截取
			
			for (PicSize picSize : picSizeList) {
				is = ByteToInputStream.byte2Input(loadFile.getBytes());
				File tempFile = File.createTempFile(UUIDGenerator.getUUID(), ext);
				OutputStream outputStream = new FileOutputStream(tempFile);
				ImageUtils.cut(is, x, y, picSize.getWidth(), picSize.getHeight(), outputStream);
				outputStream.flush();
				outputStream.close();
				fastResult = FastDfsUtils.uploadFile(tempFile.getAbsolutePath(), ext,null);
				list.add(fastResult);
				tempFile.delete();
				if(is!=null){
					try {
						is.close();
						is = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			logger.error("文件切割出错");
		}
		return list;
	}
	
	/**
	 * 批量图片 剪切并上传剪切的图片，将原图片和剪裁的图片都放回给前台
	 * 
	 * @param fileIdList
	 *            图片的地址
	 * @param picSizeList
	 *            剪切的大小
	 * @return
	 * @throws MyException
	 * @throws IOException
	 */
	public static List<FastResult> cutAndUploadFile(List<FastResult> fileIdList,
			List<PicSize> picSizeList) throws IOException, MyException {

		List<FastResult> list = new ArrayList<FastResult>();
		for (FastResult tempResult : fileIdList) {
			LoadFile loadFile = FastDfsUtils.downFile(tempResult.getFileId());
			InputStream is = ByteToInputStream.byte2Input(loadFile.getBytes());
			String ext = loadFile.getNameValuePairMap().get(
					FastDfsUtils.FILE_EXT_NAME);
			FastResult fastResult = null;
			for (PicSize picSize : picSizeList) {
				File file = ImageUtils.cut(is, ext, picSize);
				fastResult = FastDfsUtils.uploadFile(file.getAbsolutePath(), ext,null);
				list.add(fastResult);
			}
		}
		return list;
	}
	
	/**
	 * 主图有进行修改，则对主图进行剪切并上传，否则不操作
	 * @param userUnid		用户标识
	 * @param oldSourceFileId			旧图片
	 * @param newSourceFileId			新图片
	 * @param picSizeDTOs	裁剪尺寸列表
	 */
	public static void coverPicCut(String userUnid, String oldSourceFileId, String newSourceFileId, List<PicSize> picSizeDTOs) {
		
		// 主图是否有修改
		boolean modifyFlag = StringUtils.isNotEmpty(oldSourceFileId)
				&& !newSourceFileId.equalsIgnoreCase(oldSourceFileId);
		if (modifyFlag) {
			PictureUtils.asynZoom(newSourceFileId, picSizeDTOs);
		}
	}
	
	
	/**
	 * 将inputstream里的内容转换成byte数组
	 * @param inputStream
	 * @return
	 */
	public static byte[] getByteFormInputStream(InputStream inputStream){
	 	ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        try{
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        } 
        }catch(Exception e){
        	e.printStackTrace();
        }
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
	}
	
	
	
}
