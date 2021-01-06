package com.lcy.dto.generalconfig;

import java.io.Serializable;
import java.util.List;

/**
 * 标签组DTO
 *
 * @author hshande@linewell.com
 * @since 2019/01/09 11:32
 */
public class TagGroupDTO implements Serializable {

    private static final long serialVersionUID = -1153909677204493763L;
    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 标签列表
     */
    private List<TagDTO> tagList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<TagDTO> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagDTO> tagList) {
        this.tagList = tagList;
    }
}
