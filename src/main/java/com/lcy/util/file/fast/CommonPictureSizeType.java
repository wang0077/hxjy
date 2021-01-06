package com.lcy.util.file.fast;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统图片压缩规格枚举
 * @author tujianqun
 */
public enum CommonPictureSizeType {
	
	/**
	 * 240X240图片尺寸
	 */
	SIZE_240_240("_240_240",240,240),
	
	/**
	 * 4:3规格图片，手机列表专用
	 * 图片规格435X326
	 * 宽度最大：435
	 * 高度最大：326
	 */
	SIZE_4_3("_ORIGINAL_4_3",435, 326),
	
	/**
	 * 压缩后的原图
	 * 宽度最大：700
	 * 高度最大：800
	 */
	SIZE_ORIGINAL("_ORIGINAL_",700,800),
	
	/**
	 * 48X48尺寸图片
	 */
	SIZE_48_48("_48_48",48,48),
	
	/**
	 * 80X80尺寸图片 说说缩略图
	 */
	SIZE_80_80("_80_80",80,80),
	
	/**
	 * 96X96图片尺寸
	 */
	SIZE_96_96("_96_96",96,96),
	/**
	 * 144X144图片尺寸
	 */
	SIZE_144_144("_144_144",144,144),
	/**
	 * 200X200图片尺寸
	 */
	SIZE_200_200("_200_200",200,200),
	/**
	 * 220X220图片尺寸
	 */
	SIZE_220_220("_220_220",220,220),
	
	/**
	 * 园区封面
	 */
	SIZE_380_285("_380_285",380,285),
	
	/**
	 * 园区图片
	 */
	SIZE_640_406("_640_406",640,406),
	
	/**
	 * 压缩后的原图--手机滚动图片规格
	 * 宽度最大：720
	 * 高度最大：330  mdy by tujianqun UED设计的图片实际尺寸是：720*330
	 */
	SIZE_720_230("_720_230",720,330),
	
	/**
	 * 16:9规格图片，详情页和首页图片
	 * 图片规格480*270
	 * 宽度最大：480
	 * 高度最大：270
	 */
	SIZE_16_9("_ORIGINAL_16_9",720, 405);
	
	/**
	 * 
	 * @param sizename 尺寸名称
	 * @param width	      宽
	 * @param height	      长
	 */
	
	CommonPictureSizeType(String sizename,int width,int height){
		 this.height = height;
		 this.width = width;
		 this.sizeName = sizename;
	}
	
	/**
	 * 规格
	 */
	private int height;
	
	private int width;
	
	
	
	/**
	 * 规格名称
	 */
	private String sizeName;

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	 
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 获取尺寸列表
	 * @return
	 */
	public static  List<PicSize> getPicSize(){
		List<PicSize> list = new ArrayList<PicSize>();
		for(CommonPictureSizeType piceSize:CommonPictureSizeType.values()){
			list.add(new PicSize(piceSize.width,piceSize.height,  piceSize.sizeName));
		}
		return list;
	}
	
	public static PicSize getPicSize(CommonPictureSizeType piceSize){
		return new PicSize( piceSize.width,piceSize.height, piceSize.sizeName);
	}
}

 
