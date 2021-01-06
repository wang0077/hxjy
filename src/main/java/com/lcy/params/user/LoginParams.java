package com.lcy.params.user;



/**
 * 登陆参数
 * @author cjianyan@linewell.com
 * @since 2017-07-31
 */
public class LoginParams extends RegisterParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6672036412200647271L;

	private int loginType;//登陆类型
	
	
	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

}
