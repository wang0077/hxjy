package com.lcy.controller.user;

import com.lcy.dto.business.TodayTaskDTO;
import com.lcy.dto.business.UserDailyScaleStatisticsPageListDTO;
import com.lcy.dto.business.UserDailyStatisticsPageListDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.common.ResponseResult;
import com.lcy.dto.scale.ProblemMiniDTO;
import com.lcy.dto.user.MyPageStatisticsDTO;
import com.lcy.dto.user.UserExtraDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.common.ConfigParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.PageParams;
import com.lcy.params.user.UserExtraParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserExtraRestService {

    ResponseResult<UserExtraDTO> getStepInfo(BaseParams params);

    ResponseResult<UserExtraDTO> saveExtraInfo(UserExtraParams params);

    ResponseResult<Boolean> saveExtraInfoNoStep(UserExtraParams params);

    ResponseResult<UserExtraDTO> agreeZQTYS(BaseParams params);

    ResponseResult<UserExtraDTO> enterMain(BaseParams params);

    ResponseResult<UserExtraDTO> enterScoff(BaseParams params);

    ResponseResult<List<ProblemMiniDTO>> getScoffProblem(BaseParams params);

    ResponseResult<UserExtraDTO> answerScoff(ConfigParams params);

    ResponseResult<UserExtraDTO> enterEat26(BaseParams params);

    ResponseResult<List<ProblemMiniDTO>> getEat26Problem(BaseParams params);

    ResponseResult<UserExtraDTO> answerEat26(ConfigParams params);

    ResponseResult<UserExtraDTO> enterPhq9(BaseParams params);

    ResponseResult<List<ProblemMiniDTO>> getPhq9Problem(BaseParams params);

    ResponseResult<UserExtraDTO> answerPhq9(ConfigParams params);

    ResponseResult<UserExtraDTO> enterGad7(BaseParams params);

    ResponseResult<List<ProblemMiniDTO>> getGad7Problem(BaseParams params);

    ResponseResult<UserExtraDTO> answerGad7(ConfigParams params);

    ResponseResult<UserExtraDTO> enterSes(BaseParams params);

    ResponseResult<List<ProblemMiniDTO>> getSesProblem(BaseParams params);

    ResponseResult<UserExtraDTO> answerSes(ConfigParams params);

    String exportExcel(Boolean laboratoryPerson, Long startTime,
                       Long endTime, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<MyPageStatisticsDTO> getMyPageStatistics(BaseParams params);

    ResponseResult<List<TodayTaskDTO>> listTodayTask(BaseParams params);

    ResponseResult<Boolean> remainSuccessCallback(BaseParams params);

    String exportExcelScale(boolean laboratoryPerson, Long startTime, Long endTime, boolean isBase,
                            Integer period, HttpServletRequest request, HttpServletResponse response);

    ResponseResult<PageResult<UserDailyScaleStatisticsPageListDTO>> pageExportScale(PageParams params);

    ResponseResult<Boolean> reset(IDParams params);
}
