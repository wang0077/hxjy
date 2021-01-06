package com.lcy.bll.user;

import com.lcy.autogenerator.entity.Label;
import com.lcy.dto.user.LabelDTO;
import com.lcy.util.business.ICommonBO;

import java.util.LinkedHashMap;
import java.util.List;

public interface ILabelBLL extends ICommonBO<Label> {

    List<LabelDTO> list(String operUserId, String nameKeyword);

    List<LabelDTO> listByIds(String ids);
}
