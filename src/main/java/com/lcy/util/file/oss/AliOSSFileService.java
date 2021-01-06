package com.lcy.util.file.oss;

import org.apache.commons.lang3.StringUtils;


/**
 * oss文件服务类
 * @author tjianqun@linewell.com
 *
 */
public class AliOSSFileService {
	
	
	/**
	 * 文件key值
	 */
	String key;
	
	public  AliOSSFileService(String key){
		this.key = key;
	}
	
	
	/**
	 * 图片比例缩放
	 * @param width	图片宽度
	 * @param height	图片高度
	 * @return	缩放后的key
	 */
	public AliOSSFileService zoom(int width, int height) {
		
		if(StringUtils.isEmpty(key)){
			return this;
		}
		
		if(!key.contains("x-oss-process")){
			key = key+"?x-oss-process=image/resize";
		}else{
			key = key+"/resize";
		}
		 key=key+",m_lfit,limit_1,w_"+width+",h_"+height+"";
		 return  this;
	}
	
	
	/**
	 * 图片剪裁,默认采用中间剪裁
	 * @return
	 */
	public AliOSSFileService crop(int width, int height) {
		
		if(StringUtils.isEmpty(key)){
			return this;
		}
		
		if(!key.contains("x-oss-process")){
			key = key+"?x-oss-process=image/crop";
		}else{
			key = key+"/crop";
		}
		 key = key+",g_center,w_"+width+",h_"+height;
		 return this;
	}
	
	
	/**
	 * 图片格式转换
	 * 
	 * @return
	 */
	public AliOSSFileService format(String format, int quality) {

		if (StringUtils.isEmpty(key)) {
			return this;
		}

		if (!key.contains("x-oss-process")) {
			key = key + "?x-oss-process=image";
		}
		
		if (StringUtils.isNotEmpty(format)) {
			key = key + "/format," + format;
			if (quality > 0 && quality <= 100) {
				key = key + "/quality,Q_" + quality;
			}
		}
		return this;
	}
	
	
	/**
	 * 获取完整地址：加上http,如果已经有http则直接返回
	 * @return
	 */
	public String filePath() {

		if (StringUtils.isEmpty(key)) {
			return "";
		}
		// add by llj 暂时解决iOS不支持webp格式的问题
		if (key.toLowerCase().contains(".webp") && !key.toLowerCase().contains("/format,")) {
			this.format("jpg", 0);
		}
		// end add
		if (key.startsWith("http://") || key.startsWith("https://")) {
			return key;
		}
		return AliOSSConfigUtils.getInstance().getImgServiceHost() + "/" + key;
	}
	
	public static void main(String[] args) {
		AliOSSFileService utils = new AliOSSFileService("example.jpg");
		//先缩放 //再 
		System.out.println(utils.zoom(400,400).crop(100, 100).filePath());
	}
	
}
