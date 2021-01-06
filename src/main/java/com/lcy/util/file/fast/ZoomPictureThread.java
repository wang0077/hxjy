package com.lcy.util.file.fast;

import java.util.List;

/**
 * 异步图片压缩处理线程
 * @author tujianqun
 * @date 2016年1月29日
 */
public class ZoomPictureThread extends Thread{
	
	public String fileId;
	
	private List<PicSize> picSizeDtos;
	
	public ZoomPictureThread(String fileId,List<PicSize> picSizeDtos){
		this.fileId = fileId;
		this.picSizeDtos = picSizeDtos;
	}
	
	@Override
	public void run() {
		PictureUtils.zoom(fileId, picSizeDtos);
	}
}
