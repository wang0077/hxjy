package com.lcy.params.business;


import com.lcy.params.common.IdsParams;
import com.lcy.type.business.ArticleCateRcmdPosEnum;

/**
 * 
 *
 * @author: lchaofu@linewell.com
 * @date:2018年5月11日
 */
public class CategoryPositonParams extends IdsParams {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8165663254269321158L;
	
	// 展示位置
	private int position = ArticleCateRcmdPosEnum.INDEX.getNo(); // 位置默认为首页;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
