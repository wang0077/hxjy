package com.lcy.bll.user;

import com.lcy.autogenerator.entity.User;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.scale.ProblemOptionBaseDTO;
import com.lcy.dto.user.UserExtraDTO;
import com.lcy.params.user.UserExtraParams;

import java.util.LinkedHashMap;
import java.util.List;

public interface IUserExtraBLL {

    UserExtraDTO getStepInfo(String userId);

    UserExtraDTO saveExtraInfo(String userId, UserExtraParams userExtraParams);

    boolean saveExtraInfoNoStep(String userId, UserExtraParams userExtraParams);

    UserExtraDTO saveStep(String userId, UserExtraParams userExtraParams);

    UserExtraDTO answerScoff(String userId, List<ProblemOptionBaseDTO> optionList);

    UserExtraDTO answerEat26(String userId, List<ProblemOptionBaseDTO> optionList);

    UserExtraDTO answerPhq9(String userId, List<ProblemOptionBaseDTO> optionList);

    UserExtraDTO answerGad7(String userId, List<ProblemOptionBaseDTO> optionList);

    UserExtraDTO answerSes(String userId, List<ProblemOptionBaseDTO> optionList);

    PageResult<User> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize);

    boolean remainSuccessCallback(String userId);

    boolean reset(String userId);
}
