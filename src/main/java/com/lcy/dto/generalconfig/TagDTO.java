package com.lcy.dto.generalconfig;

import java.io.Serializable;

/**
 * 标签DTO
 *
 * @author hshande@linewell.com
 * @since 2019/01/09 11:32
 */
public class TagDTO implements Serializable {

    private static final long serialVersionUID = 1698766538427677321L;

    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 选中
     */
    private boolean check;

    /**
     * 是否是更多
     */
    private boolean more;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }
}
