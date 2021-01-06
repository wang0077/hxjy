package com.lcy.util.file.fast;

/**
 * 图片size dto
 * @author cjianyan@linewell.com
 * @since  2015年8月26日
 */
public class CustomPicSize {
	
	/**
	 * 构造函数
	 * @param x			横坐标
	 * @param y			纵坐标
	 * @param width			长度
	 * @param height		宽度
	 */
	public CustomPicSize(int x,int y,int width, int height){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.setName(width + FileUrlConstants.UNDER_LINE + height);
	}
	
	
	
	private int x;
	
	private int y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	/**
	 * 宽度
	 */
	private int width;
	
	/**
	 * 高度
	 */
	private int height;
	
	/**
	 * 图片名称
	 */
	private String name;
	

	/**
	 * 获取图片名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置图片名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取宽度
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 设置宽度
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 获取高度
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 设置高度
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
}
