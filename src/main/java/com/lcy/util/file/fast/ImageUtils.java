package com.lcy.util.file.fast;

import com.lcy.util.common.UUIDGenerator;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * 图片处理工具类
 * 
 * @author zqiang
 * @since 2015年8月25日
 */
public class ImageUtils {

	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	/**
	 * 默认裁剪宽高
	 */
	public static final int DEFAULT_VALUE = 10000;

	
	/**
	 * 等比例缩放
	 * @param inputStream	输入文件流
	 * @param width			宽度
	 * @param height		高度
	 * @param outputStream  输出文件流
	 * @return				是否成功
	 */
	public static boolean zoomWidthConstrain(InputStream inputStream, int width, int height,
			OutputStream outputStream){
		try {
			Thumbnails.of(inputStream).outputQuality(1.0).size(width, height).toOutputStream(outputStream);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 图片缩放
	 * 
	 * @param inputStream
	 *            转化前图片输入流
	 * @param width
	 *            缩放的宽度
	 * @param height
	 *            缩放的高度
	 * @param outputStream
	 *            转化后图片输出流
	 * @return 转化成功与否(true or false)
	 * @throws IOException
	 * @throws Exception
	 */
	public static boolean zoom(InputStream inputStream, int width, int height,
			OutputStream outputStream){

	// 起始坐标
	int x = 0;
	int y = 0;
	
	if (null == inputStream || null == outputStream) {
		return false;
	}
	
	File zoomFile = null;	
	
	try{
		if (!(width > 0 && height > 0)) {
			return false;
		}
		// 如果图片小于剪切的图片需要放大图片
		BufferedImage sourceImg = ImageIO.read(inputStream);
		float widthScal = 1f;
		float heightScal = 1f;
		float maxScal = 1f;

		// 图片原始大小
		int sourceImgWidth = sourceImg.getWidth();
		int sourceImgHeght = sourceImg.getHeight();

		// 计算缩放比例
		widthScal = (float) (width / (sourceImgWidth + 0.0));
		heightScal = (float) (height / (sourceImgHeght + 0.0));

		if (widthScal > heightScal) {
			maxScal = widthScal;
		} else {
			maxScal = heightScal;
		}

		// 稍微放大一点图片，防止由于精度问题导致出错
		// maxScal = maxScal + 0.01f;

		// 计算起始坐标
		int scalImageWith = (int) (maxScal * sourceImgWidth);
		int scalImageHeight = (int) (maxScal * sourceImgHeght);
		x = (scalImageWith - width) / 2;
		y = (scalImageHeight - height) / 2;
		zoomFile = File.createTempFile("zoom", ".png");
		Thumbnails.of(sourceImg).scale(maxScal).outputQuality(1.0).toFile(zoomFile);
		// 指定坐标裁剪
		
		Thumbnails.of(zoomFile).sourceRegion(x, y, width, height)
				.outputQuality(1.0).size(width, height)
				.keepAspectRatio(true).toOutputStream(outputStream);
		
		sourceImg = null;

		} catch (IOException e) {
			logger.error("文件压缩失败！");
			return false;
		}finally{
			if(zoomFile!=null){
				 zoomFile.delete();
			}
			try {
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * @param fromImagePath
	 *            转化前图片地址
	 * @param x
	 *            裁剪开始位置x坐标
	 * @param y
	 *            裁剪开始位置y
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param toImagePath
	 *            转化后图片地址
	 * @return 转化成功与否（true or false）
	 * @throws Exception
	 */
	public static boolean cut(String fromImagePath, int x, int y, int width,
			int height, String toImagePath) throws Exception {
		if (!(width > 0 && height > 0)) {
			return false;
		}

		try {

			// 指定坐标裁剪
			Thumbnails.of(fromImagePath).sourceRegion(x, y, width, height)
					.outputQuality(1.0).size(width, height)
					.keepAspectRatio(false).toFile(toImagePath);

		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return true;
	}

	/**
	 * @param inputStream
	 *            转化前图片输入流
	 * @param x
	 *            裁剪开始位置x坐标
	 * @param y
	 *            裁剪开始位置y
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param outputStream
	 *            转化后图片输出流
	 * @return 转化成功与否（true or false）
	 * @throws Exception
	 */
	public static boolean cut(InputStream inputStream, int x, int y, int width,
			int height, OutputStream outputStream) {

		if (null == inputStream || null == outputStream) {
			return false;
		}

		if (!(width > 0 && height > 0)) {
			return false;
		}
		File tempFile = null;
		try {
			tempFile = File.createTempFile("temp", null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		inputstreamtofile(inputStream, tempFile);

		// 如果图片小于剪切的图片需要放大图片
		BufferedImage sourceImg = null;
		try {
			sourceImg = ImageIO.read(tempFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		float widthScal = 1f;
		float heightScal = 1f;
		float maxScal = 1f;
		if ((x + width) > sourceImg.getWidth()) {
			widthScal = (float) (width / (sourceImg.getWidth() + 0.0));
		}
		if ((y + height) > sourceImg.getHeight()) {
			heightScal = (float) (height / (sourceImg.getHeight() + 0.0));
		}
		if (widthScal > heightScal) {
			maxScal = widthScal;
		} else {
			maxScal = heightScal;
		}
		File zoomFile = null;
		try {
			// 需要缩放
			if (maxScal > 1) {
				zoomFile = File.createTempFile("zoom", ".png");
				Thumbnails.of(tempFile).scale(maxScal).outputQuality(1.0)
						.toFile(zoomFile);
				// 指定坐标裁剪
				Thumbnails.of(zoomFile).sourceRegion(x, y, width, height)
						.outputQuality(1.0).size(width, height)
						.keepAspectRatio(true).toOutputStream(outputStream);
			} else {
				// 指定坐标裁剪
				Thumbnails.of(tempFile).sourceRegion(x, y, width, height)
						.outputQuality(1.0).size(width, height)
						.keepAspectRatio(true).toOutputStream(outputStream);
			}
			
			sourceImg = null;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(zoomFile!=null){
				zoomFile.delete();
			}
			if(tempFile!=null){
				tempFile.delete();
			}
		}
		return true;
	}

	/** */
	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param srcFile
	 *            -- 目标图片
	 * @param fontName
	 *            -- 字体名
	 * @param fontStyle
	 *            -- 字体样式
	 * @param color
	 *            -- 字体颜色
	 * @param fontSize
	 *            -- 字体大小
	 * @param x
	 *            -- 偏移量
	 * @param y
	 */
	public static void pressText(String pressText, File srcFile,
			String fontName, int fontStyle, int color, int fontSize, int x,
			int y) {
		try {
			Image src = ImageIO.read(srcFile);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(new Color(color, false));
			g.setFont(new Font(fontName, fontStyle, fontSize));
			width = width - (fontSize * pressText.length())/2 - x;
			height = height - fontSize / 2 - y;
			g.drawString(pressText, width,height);
			g.dispose();
			saveImageUsingJPGWithQuality(image, srcFile, 1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void inputstreamtofile(InputStream ins, File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				os = null;
			}
		}

	}

	/**
	 * save jpg with specified quality. 为了图片质量，quality的值不能太低。
	 * 
	 * @param image
	 *            图片
	 * @param file
	 *            保存的文件
	 * @param quality
	 *            图片质量
	 **/
	public static void saveImageUsingJPGWithQuality(BufferedImage image,
			File file, float quality) throws Exception {

		BufferedImage newBufferedImage = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		newBufferedImage.getGraphics().drawImage(image, 0, 0, null);

		Iterator<ImageWriter> iter = ImageIO
				.getImageWritersByFormatName("jpeg");

		ImageWriter imageWriter = iter.next();
		ImageWriteParam iwp = imageWriter.getDefaultWriteParam();

		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwp.setCompressionQuality(quality);

		FileImageOutputStream fileImageOutput = new FileImageOutputStream(file);
		imageWriter.setOutput(fileImageOutput);
		IIOImage iio_image = new IIOImage(newBufferedImage, null, null);
		imageWriter.write(null, iio_image, iwp);
		imageWriter.dispose();
	}

	/**
	 * 水印文字
	 * 
	 * @param srcFile
	 *            源图片
	 * @param text
	 *            文字
	 */
	public static void watermark(File srcFile, String text) {
		pressText(text, srcFile, "宋体", 0, 0x000000, 12, 10, 10);
		pressText(text, srcFile, "宋体", 0, 0xffffff, 12, 11, 11);
	}

	/**
	 * 切图
	 * @param is				文件输入流
	 * @param fileExtName		后缀名称
	 * @param picSize			切图大小对象
	 * @return
	 */
	public static File cut(InputStream is, String fileExtName, PicSize picSize) {

		File tempFile = null;

		String prefix = UUIDGenerator.getUUID();
		String suffix = getFileNameExt(fileExtName);

		// 存放剪切完的图片输出流
		OutputStream outputStream = null;

		try {
			tempFile = File.createTempFile(prefix, suffix);
			outputStream = new FileOutputStream(tempFile);
			int width = 0;
			int height = 0;

			// 如果高度为0，则按默认值
			if (picSize.getHeight() == 0) {
				width = picSize.getWidth();
				height = ImageUtils.DEFAULT_VALUE;

			} else if (picSize.getWidth() == 0) {
				width = ImageUtils.DEFAULT_VALUE;
				height = picSize.getHeight();
			} else {
				width = picSize.getWidth();
				height = picSize.getHeight();
			}

			ImageUtils.zoom(is, width, height, outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return tempFile;
	}

	private static String getFileNameExt(String fileName)
	{
		String ext = "";
		if (StringUtils.isNotEmpty(fileName.trim())) {
			int index = fileName.lastIndexOf(".");
			if (index > -1) {
				ext = fileName.substring(index);
			}
		}
		return ext;
	}

	public static void main(String[] args) {
		
		//ImageUtils.cut(fromImagePath, x, y, width, height, toImagePath);
		
		 /*try {
		
		 Thumbnails.of("D:/1.jpg")
		 .size(300, 300)
		 .keepAspectRatio(true)
		 .toFile("D:/2.jpg");
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 watermark(new File("D:/1.jpg"), "杨小二工作室-给图片添加水印测试");*/
		
		//图片缩放测试
		try {
			FileInputStream in = new FileInputStream("D:/1.jpg");
			FileOutputStream out = new FileOutputStream("D:/2018.jpg");		
			zoom(in, 400, 400, out);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//图片裁剪测试
		try {
			cut("D:/1.jpg", 3, 6, 100, 100, "D:/720.jpg");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//图片添加水印测试
		watermark(new File("D:/1.jpg"), "杨小二工作室-给图片添加水印测试");
		
	}

}
