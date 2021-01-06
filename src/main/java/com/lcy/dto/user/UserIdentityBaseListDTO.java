package com.lcy.dto.user;

import com.lcy.params.common.AppLastDateBean;

import java.io.Serializable;

/**
 * 用户身份基类(列表)
 * @author syangen@linewell.com
 * @since 2017-11-15
 *
 */
public class UserIdentityBaseListDTO extends AppLastDateBean implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8164126035427145516L;

	/**
	 * 官方用户类型
	 */
	private int officialType;
	
	/**
	 * 官方用户类型中文
	 */
	private String officialTypeCn;

	/**
	 * 获取官方用户类型
	 * @return 官方用户类型
	 */
	public int getOfficialType() {
		return officialType;
	}

	/**
	 * 设置官方用户类型
	 * @param officialType 官方用户类型
	 */
	public void setOfficialType(int officialType) {
		this.officialType = officialType;
	}

	/**
	 * 获取官方用户类型中文
	 * @return 官方用户类型中文
	 */
	public String getOfficialTypeCn() {
		return officialTypeCn;
	}

	/**
	 * 设置官方用户类型中文
	 * @param officialTypeCn 官方用户类型中文
	 */
	public void setOfficialTypeCn(String officialTypeCn) {
		this.officialTypeCn = officialTypeCn;
	}
}
