package com.lcy.params.user;


import com.lcy.params.common.BaseParams;

/**
 * 用户第三方类型参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-11-16
 */
public class UserThirdTypeParams extends BaseParams {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */
	private String id;
    /**
     * 第三方类型
     */
	private Integer thirdType;

	/**
	 * 用户标识
	 */
	private String userId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getThirdType() {
		return thirdType;
	}

	public void setThirdType(Integer thirdType) {
		this.thirdType = thirdType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
