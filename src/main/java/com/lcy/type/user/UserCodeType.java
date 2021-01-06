package com.lcy.type.user;

/**
 * 用户异常编码枚举
 *
 * @author lshengda@linewell.com
 * @since 2017年5月24日
 */
public enum UserCodeType {

	/**
	 * 手机号和邮箱不能同时为空
	 */
	MOBILE_EMAIL_ALL_EMPTY(101001,"手机号和邮箱不能同时为空"),
	
	/**
	 * 密码不能为空
	 */
	PASSWORD_EMPTY(101002,"密码不能为空"),
	
	/**
	 * 手机号格式不正确
	 */
	MOBILE_FORMAT_ERROR(101003,"手机号格式不正确"),
	
	/**
	 * 邮箱格式不正确
	 */
	EMAIL_FORMAT_ERROR(101004,"邮箱格式不正确"),
	
	/**
	 * 该用户已经注册
	 */
	USER_EXIST(101005,"该用户已经注册"),
	
	/**
	 * 保存用户信息异常
	 */
	USER_INFO_SAVE_EXCEPTION(101006,"保存用户信息异常"),
	
	/**
	 * 登录名为空
	 */
	LOGIN_NAME_EMPTY(101007,"登录名为空"),
	
	/**
	 * 用户未注册
	 */
	USER_NOT_EXIST(101008,"用户未注册"),
	
	/**
	 * 密码错误
	 */
	PASSWORD_ERROR(101009,"密码输入错误"),
	
	/**
	 * 手机号为空
	 */
	MOBILE_EMPTY(101010,"手机号为空"),
	
	/**
	 * 邮箱为空
	 */
	EMAIL_EMPTY(101011,"邮箱为空"),
	
	/**
	 * 旧密码错误
	 */
	OLD_PASSWORD_ERROR(101012,"旧密码输入错误"),
	
	/**
	 * 用户注册异常
	 */
	USER_REGISTER_EXCEPTION(101013,"用户注册异常"),
	
	/**
	 * 登录异常
	 */
	LOGIN_EXCEPTION(101014,"登录异常"),
	
	/**
	 * 验证手机号异常
	 */
	VALIDATE_MOBILE_EXCEPTION(101015,"验证手机号异常"),
	
	/**
	 * 验证邮箱异常
	 */
	VALIDATE_EMAIL_EXCEPTION(101016,"验证邮箱异常"),
	
	/**
	 * 修改密码异常
	 */
	CHANGE_PASSWORD_EXCEPTION(101017,"修改密码异常"),
	

	
	/**
	 * 更新手机号异常
	 */
	CHANGE_MOBILE_EXCEPTION(101018,"更新手机号异常"),
	
	/**
	 * 获取用户异常
	 */
	GET_USER_EXCEPTION(101019,"获取用户异常"),
	
	/**
	 * 重置密码异常
	 */
	RESET_PASSWORD_EXCEPTION(101020,"重置密码异常")
	
	/**
	 * 修改头像异常
	 */
	,CHANGE_PHOTO_EXCEPTION(101121,"修改头像异常")
	
	/**
	 * 修改昵称异常
	 */
	,CHANGE_NICKNAME_EXCEPTION(101121,"修改昵称异常")
	
	/**
	 * 身份证号码已存在
	 */
	,CARDID_EXISTS(101122,"身份证号码已存在")
	
	/**
	 * 营业许可证号已存在
	 */
	,LICENSEID_EXISTS(101122,"营业许可证号已存在")
	
	/**
	 * 组织架构证号已存在
	 */
	,ORGID_EXISTS(101122,"机构代码已存在")
	
	/**
	 * 昵称已存在
	 */
	,NICKNAME_EXISTS(101123,"该昵称已有人使用，换一个试试吧"),
	
	/**
	 * 没有第三方信息，请先授权登录
	 */
	THIRD_INFO_NOEXIT(101124, "没有第三方信息，请先授权登录"),
	
	/**
	 * 手机号已绑定第三方账号
	 */
	PHONE_BOUND_THIRD(101125, "已绑定"),
	
	
	/**
	 * 
	 */
	SESSION_KEY_ISEMPTY(101126, "请重新授权登录"),
	
	/**
	 * 登录异常
	 */
	USER_DISABLE(101027,"当前用户已被锁定，请联系客服"),
	
	/**
	 * 身份证正面错误
	 */
	CARD_FRONT_ERROR(101028,"身份证正面不符合要求"),
	
	/**
	 * 身份证反面错误
	 */
	CARD_BACK_ERROR(101029,"身份证反面不符合要求");
	
	/**
	 * 编码
	 */
	private int no;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 获取编码
	 * @return
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 设置编码
	 * @param no
	 */
	public void setNo(int no) {
		this.no = no;
	}

	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	UserCodeType(int no, String name){
		this.no = no;
		this.name = name;
	}
	
	/**
	 * 获取枚举
	 * @param no	序号
	 * @return
	 */
	public static UserCodeType getType(int no){
		for (UserCodeType type : UserCodeType.values()) {
			if (type.getNo() == no) {
				return type;
			}
		}
		return null;
	}
}
