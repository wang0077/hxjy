package com.lcy.dto.generalconfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构dto
 * 
 * @author tjianqun@linewell.com
 *
 * @date 2017年5月16日 上午9:20:24
 */
public class AreaTreeDTO {
	
	private AreaConfigDTO config;

	List<AreaTreeDTO> children;

	public AreaConfigDTO getConfig() {
		return config;
	}

	public void setConfig(AreaConfigDTO config) {
		this.config = config;
	}
	
	public void add(AreaTreeDTO sub){
		if(children==null){
			children = new ArrayList<AreaTreeDTO>();
		}
		children.add(sub);
	}

	public List<AreaTreeDTO> getChildren() {
		return children;
	}

	public void setChildren(List<AreaTreeDTO> children) {
		this.children = children;
	}
	
}
