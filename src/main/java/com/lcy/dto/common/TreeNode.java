package com.lcy.dto.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形对象
 * 
 * @author syangen@linewell.com
 * @since 2017-09-06
 */
public class TreeNode implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7344223482661293008L;
	
	// 树形节点ID
	private String id;

	// 节点名称
	private String name;

	// 节点状态 tree 表示不是叶子节点，false表示是叶子节点
	private boolean isParent;

	// 父节点id
	private String pId;
	
	// 节点图标
	private String iconSkin;

	// 子节点
	private List<TreeNode> children;

	// 属性
	private Map<String, String> attributes;

	// 是否选中
	private boolean checked;

	// 是字典时，对应字典的值
	private String value;

	// 此节点不显示 checkbox / radio
	private boolean nocheck;

	// 是否展开
	private boolean open;

	/**
	 * 获取是否被选中
	 * 
	 * @return
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * 设置选中
	 * 
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * 获取节点的ID
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置节点的ID
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取显示的名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置显示的名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取节点状态 tree 表示不是叶子节点，false表示是叶子节点
	 * 
	 * @return
	 */
	public boolean isIsParent() {
		return isParent;
	}

	/**
	 * 设置节点状态 tree 表示不是叶子节点，false表示是叶子节点
	 * 
	 * @param isParent
	 */
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	/**
	 * 获取节点的图标样式
	 * 
	 * @return
	 */
	public String getIconSkin() {
		return iconSkin;
	}

	/**
	 * 设置节点的图标样式
	 * 
	 * @param iconSkin
	 */
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	/**
	 * 是字典时，对应字典的值
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 是字典时，对应字典的值
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取子节点列表
	 * 
	 * @return
	 */
	public List<TreeNode> getChildren() {
		return children;
	}

	/**
	 * 设置子节点列表
	 * 
	 * @param childrens
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	/**
	 * 获取节点的自定义的属性
	 * 
	 * @return
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * 设置节点的自定义的属性
	 * 
	 * @param attributes
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * 获取节点的父节点
	 */
	public String getpId() {
		return pId;
	}

	/**
	 * 设置节点的父节点
	 * 
	 * @param pId
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}

	/**
	 * 获取此节点不显示 checkbox / radio
	 * 
	 * @return the nocheck
	 */
	public boolean isNocheck() {
		return nocheck;
	}

	/**
	 * 设置此节点不显示 checkbox / radio
	 * 
	 * @param nocheck
	 *            the nocheck to set
	 */
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	/**
	 * 返回是否展开
	 * 
	 * @return
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * 设置是否展开
	 * 
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
}
