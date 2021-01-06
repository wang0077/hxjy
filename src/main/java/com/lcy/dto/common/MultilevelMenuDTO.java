package com.lcy.dto.common;

import java.io.Serializable;
import java.util.List;

/**
 * 多级联动的结构
 * @author shaolong@linewell.com
 * @since 2018-08-07 16:12
 **/
public class MultilevelMenuDTO implements Serializable {

    private static final long serialVersionUID = -4255704845353946113L;

    /**
     * 分类Id
     */
    private String id;

    /**
     * 分类名称
     */
    private String label;

    /**
     * 分类seq
     */
    private String seqNum;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 是否是叶子节点
     */
    private boolean isLeaf;

    /**
     * 是否是显示状态
     */
    private boolean show;

    /**
     * 叶子节点
     */
    private List<MultilevelMenuDTO> chilrden;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MultilevelMenuDTO> getChilrden() {
        return chilrden;
    }

    public void setChilrden(List<MultilevelMenuDTO> chilrden) {
        this.chilrden = chilrden;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
