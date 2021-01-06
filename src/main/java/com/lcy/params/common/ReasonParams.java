package com.lcy.params.common;

/**
 * 原因参数
 *
 * @author lchunyi@linewell.com
 * @since 2017-9-21
 */
public class ReasonParams extends IDParams {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7946244706481246385L;
	
	/**
	 * 原因
	 */
	private	String reason;

	/**
	 * 举报资源类型
	 */
	private	String resourceType;

	/**
	 * 是否删除
	 */
	private Integer isDeleted;

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
