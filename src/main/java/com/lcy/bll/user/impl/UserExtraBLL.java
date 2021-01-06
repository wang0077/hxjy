package com.lcy.bll.user.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lcy.autogenerator.entity.User;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.entity.UserThirdInfo;
import com.lcy.autogenerator.mapper.UserMapper;
import com.lcy.autogenerator.service.IUserAccountService;
import com.lcy.autogenerator.service.IUserService;
import com.lcy.autogenerator.service.IUserThirdInfoService;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.bll.business.IWeightRecordBLL;
import com.lcy.bll.user.IUserExtraBLL;
import com.lcy.bll.user.IUserInfoEditServiceBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.dto.scale.ProblemOptionBaseDTO;
import com.lcy.dto.user.UserExtraDTO;
import com.lcy.params.common.BaseParams;
import com.lcy.params.user.UserExtraParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.type.business.UserStepEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserExtraBLL implements IUserExtraBLL {

    @Autowired
    IUserService userService;

    @Autowired
    IUserDailyStatisticsBLL userDailyStatisticsBLL;

    @Autowired
    IWeightRecordBLL weightRecordBLL;

    @Autowired
    IUserInfoEditServiceBLL userInfoEditServiceBLL;

    @Autowired
    IUserThirdInfoService userThirdInfoService;

    @Autowired
    IUserAccountService userAccountService;

    @Override
    public UserExtraDTO getStepInfo(String userId) {
        User user = userService.selectById(userId);
        if (user != null){
            return getUserExtraDTO(user);
        }
        return null;
    }

    @Override
    public UserExtraDTO saveExtraInfo(String userId, UserExtraParams userExtraParams) {

        User user = ModelMapperUtils.map(userExtraParams, User.class);
        user.setId(userId);
        user.setStep(UserStepEnum.BMI.getNo());

        Float weight = Float.parseFloat(userExtraParams.getWeight());
        Float height = Float.parseFloat(userExtraParams.getHeight()) / 100;
        user.setBmi(String.valueOf(weight/(height * height)));

        // 第一次保存体重记录到体重检测
        User oldUser = userService.selectById(userId);
        if (StringUtils.isEmpty(oldUser.getWeight())){
            weightRecordBLL.save(userId, userExtraParams.getWeight());
        }

        boolean flag = userService.updateById(user);
        if (flag){
            return getUserExtraDTO(userService.selectById(user));
        }
        return null;
    }

    @Override
    public boolean saveExtraInfoNoStep(String userId, UserExtraParams userExtraParams) {

        User user = ModelMapperUtils.map(userExtraParams, User.class);
        user.setId(userId);

        Float weight = Float.parseFloat(userExtraParams.getWeight());
        Float height = Float.parseFloat(userExtraParams.getHeight()) / 100;
        user.setBmi(String.valueOf(weight/(height * height)));

        return userService.updateById(user);
    }

    @Override
    public UserExtraDTO saveStep(String userId, UserExtraParams userExtraParams) {

        User user = ModelMapperUtils.map(userExtraParams, User.class);
        user.setId(userId);

        boolean flag = userService.updateById(user);
        if (flag){
            return getUserExtraDTO(userService.selectById(user));
        }
        return null;
    }

    @Override
    public UserExtraDTO answerScoff(String userId, List<ProblemOptionBaseDTO> optionList) {
        int score = 0;
        for (ProblemOptionBaseDTO problemOptionBaseDTO : optionList) {
            if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "1")){
                score++;
            }
        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        boolean flag = userDailyStatisticsBLL.saveScale(userId, UserDailyStatisticsTypeEnum.SCOFF, date,
                null, null, score + "");
        if (flag){

            UserExtraParams userExtraParams = new UserExtraParams();
            userExtraParams.setStep(UserStepEnum.SCOFF_RESULT.getNo());
            userExtraParams.setLastScaleTime(System.currentTimeMillis());
            return saveStep(userId, userExtraParams);
        }

        return null;
    }

    @Override
    public UserExtraDTO answerEat26(String userId, List<ProblemOptionBaseDTO> optionList) {
        int score = 0;
        for (ProblemOptionBaseDTO problemOptionBaseDTO : optionList) {
            if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "1")){
                score += 5;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "2")){
                score += 4;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "3")){
                score += 3;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "4")){
                score += 2;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "5")){
                score += 1;
            }
        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        boolean flag = userDailyStatisticsBLL.saveScale(userId, UserDailyStatisticsTypeEnum.EAT_26, date,
                null, null, score + "");
        if (flag){

            UserExtraParams userExtraParams = new UserExtraParams();
            userExtraParams.setStep(UserStepEnum.EAT_26_RESULT.getNo());
            userExtraParams.setLastScaleTime(System.currentTimeMillis());
            return saveStep(userId, userExtraParams);
        }

        return null;
    }

    @Override
    public UserExtraDTO answerPhq9(String userId, List<ProblemOptionBaseDTO> optionList) {
        int score = 0;
        for (ProblemOptionBaseDTO problemOptionBaseDTO : optionList) {
            if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "2")){
                score += 1;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "3")){
                score += 2;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "4")){
                score += 3;
            }
        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        boolean flag = userDailyStatisticsBLL.saveScale(userId, UserDailyStatisticsTypeEnum.PHQ_9, date,
                null, null, score + "");
        if (flag){

            UserExtraParams userExtraParams = new UserExtraParams();
            userExtraParams.setStep(UserStepEnum.PHQ_9_RESULT.getNo());
            userExtraParams.setLastScaleTime(System.currentTimeMillis());
            return saveStep(userId, userExtraParams);
        }

        return null;
    }

    @Override
    public UserExtraDTO answerGad7(String userId, List<ProblemOptionBaseDTO> optionList) {
        int score = 0;
        for (ProblemOptionBaseDTO problemOptionBaseDTO : optionList) {
            if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "2")){
                score += 1;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "3")){
                score += 2;
            } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "4")){
                score += 3;
            }
        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        boolean flag = userDailyStatisticsBLL.saveScale(userId, UserDailyStatisticsTypeEnum.GAD_7, date,
                null, null, score + "");
        if (flag){

            UserExtraParams userExtraParams = new UserExtraParams();
            userExtraParams.setStep(UserStepEnum.GAD_7_RESULT.getNo());
            userExtraParams.setLastScaleTime(System.currentTimeMillis());
            return saveStep(userId, userExtraParams);
        }

        return null;
    }

    @Override
    public UserExtraDTO answerSes(String userId, List<ProblemOptionBaseDTO> optionList) {
        int score = 0;
        for (ProblemOptionBaseDTO problemOptionBaseDTO : optionList) {

            if (StringUtils.equals(problemOptionBaseDTO.getProblemId(), "3") ||
                    StringUtils.equals(problemOptionBaseDTO.getProblemId(), "5") ||
                    StringUtils.equals(problemOptionBaseDTO.getProblemId(), "8") ||
                    StringUtils.equals(problemOptionBaseDTO.getProblemId(), "9") ||
                    StringUtils.equals(problemOptionBaseDTO.getProblemId(), "10")){

                if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "1")){
                    score += 1;
                } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "2")){
                    score += 2;
                } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "3")){
                    score += 3;
                } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "4")){
                    score += 4;
                }
            } else {
                if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "1")){
                    score += 4;
                } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "2")){
                    score += 3;
                } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "3")){
                    score += 2;
                } else if (StringUtils.equals(problemOptionBaseDTO.getProblemOptionId(), "4")){
                    score += 1;
                }
            }

        }

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        boolean flag = userDailyStatisticsBLL.saveScale(userId, UserDailyStatisticsTypeEnum.SES, date,
                null, null, score + "");
        if (flag){

            UserExtraParams userExtraParams = new UserExtraParams();
            userExtraParams.setStep(UserStepEnum.SES_RESULT.getNo());
            userExtraParams.setLastScaleTime(System.currentTimeMillis());
            return saveStep(userId, userExtraParams);
        }
        return null;
    }

    @Override
    public PageResult<User> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();


        boolean laboratoryPerson = (boolean) filterMap.get("laboratoryPerson");

        if (laboratoryPerson){
//            wrapper.isNotNull("ED_ID");
            wrapper.like("ED_ID", "ED", SqlLike.RIGHT);
        } else {
//            wrapper.isNull("ED_ID");
//            wrapper.or();
            wrapper.like("ED_ID", "HC", SqlLike.RIGHT);
        }
        wrapper.orderBy("ED_ID", true);

        Page<User> userPage = userService.selectPage(new Page<User>(pageNo, pageSize), wrapper);
        List<User> records = userPage.getRecords();
//        List<String> userIdList = new ArrayList<>();
//        if (records != null && !records.isEmpty()){
//            for (User record : records) {
//                userIdList.add(record.getId());
//            }
//        }

        PageResult<User> result = new PageResult<>();
        result.setTotal(userPage.getTotal());
        result.setList(records);
        result.setPageSize(userPage.getSize());
        result.setCurPage(userPage.getCurrent());

        return result;
    }

    @Override
    public boolean remainSuccessCallback(String userId) {
        User user = userService.selectById(userId);
        user.setIsScaleSecond(BooleanType.TRUE.getCode());
//        user.setIsSecondDone(BooleanType.FALSE.getCode());
        user.setScalePeriodRemain(BooleanType.FALSE.getCode());
        return userService.updateById(user);
    }

    @Override
    @Transactional
    public boolean reset(String userIds) {
        String[] userIdArr = userIds.split(InnoPlatformConstants.COMMA_EN);
        boolean flag = false;
        for (String id : userIdArr) {
            User user = userService.selectById(id);
            String phone = user.getPhone();
            if (StringUtils.isEmpty(phone) || StringUtils.isNotEmpty(user.getDeprecatedPhone())) {
                flag = true;
                continue;
            }
            user.setDeprecatedPhone(phone);
            user.setPhone("");

            BaseParams baseParams = new BaseParams();
            UserThirdInfo userThirdInfoBean = userInfoEditServiceBLL.getUserThirdInfoBean(baseParams.getMiniId(), id, baseParams.getAppId());
            if (userThirdInfoBean != null) {
                userThirdInfoBean.setUserId("");
            }
            flag = userService.updateById(user) && userAccountService.deleteById(id) && (userThirdInfoBean == null || userThirdInfoService.updateById(userThirdInfoBean));
        }
        return flag;
    }

    UserExtraDTO getUserExtraDTO(User user){
        UserExtraDTO userExtraDTO = new UserExtraDTO();
        Integer step = user.getStep();
        userExtraDTO.setStep(step);
        userExtraDTO.setStepCn(UserStepEnum.getTypeName(step));
        String stepWord = "";
        boolean canEnterMain = false;

        // 后测量表
        userExtraDTO.setSecond(user.getIsScaleSecond() != null && user.getIsScaleSecond() == BooleanType.TRUE.getCode());

        // 不是后测量表
//        if (!userExtraDTO.isSecond() && user.getLastScaleTime() != null && user.getStep() == UserStepEnum.MAIN.getNo()){

            String startDate = DateUtils.parseTimeToDate(user.getRegisterTime());
            String endDate = DateUtils.parseTimeToDate(System.currentTimeMillis());
            int day = DateUtils.diffDay(startDate, endDate) + 1;
            if (day >= 28 && day % 28 >= 0 && day % 28 <= 7){
                int period = day / 27;

                // 更新周期信息
                if (user.getScalePeriod() == null || user.getScalePeriod() != period) {
                    user.setScalePeriod(period);
                    user.setIsPeriodDone(BooleanType.FALSE.getCode());
                    user.setScalePeriodRemain(BooleanType.TRUE.getCode());
                    userService.updateById(user);
                }
                userExtraDTO.setRemainSecond(user.getScalePeriodRemain() == BooleanType.TRUE.getCode());
            }
//        }

        String userId = user.getId();
        UserDailyStatistics userDailyStatistics;
        if (step == UserStepEnum.BMI.getNo()){
            String birthday = user.getBirthday();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            String currYear = simpleDateFormat.format(new Date());
            int age = Integer.parseInt(currYear) - Integer.parseInt(birthday.substring(0, 4));
            int gender = user.getGender();
            if (higher(age, gender, Float.parseFloat(user.getBmi()))){
                stepWord = "进入进食问题量表筛查。";
            } else {
                stepWord = "你的BMI小于正常下限，建议加强注意营养，并关注其他身体指标，若存在以下体征：体重下降, 月经紊乱、停经、脱发、身体怕冷、睡眠困难和疲倦，头晕，胃疼，便秘，体表长出绒毛，肤质差，建议至上海市精神卫生中心进食障碍诊治专病门诊（上海市零陵路604号）就诊。接下来进入进食问题量表筛查。";
                canEnterMain = true;
            }
        } else if (step == UserStepEnum.SCOFF_RESULT.getNo()){
//            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.SCOFF);
//            if (userDailyStatistics != null){
//                String value = userDailyStatistics.getValue();
//                if (Integer.parseInt(value) < 2){
//                    stepWord = "恭喜你！没有进食障碍倾向！但是你依然可以浏览本程序内容";
//                    canEnterMain = true;
//                    updateUserSecondDone(user);
//                } else {
//                    stepWord = "经过测评，你可能存在进食方面的问题，建你进一步完成详细评估";
//                }
//            }
        } else if (step == UserStepEnum.EAT_26_RESULT.getNo()) {
            int scoffValue = 0, eatValue = 0;
            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.SCOFF);
            if (userDailyStatistics != null){
                scoffValue = Integer.parseInt(userDailyStatistics.getValue());
            }
            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.EAT_26);
            if (userDailyStatistics != null){
                eatValue = Integer.parseInt(userDailyStatistics.getValue());
            }

            if (scoffValue < 2 || (scoffValue >= 2 && eatValue < 16)) {
                stepWord = "经过评估，你存在进食问题的风险较低！但是如果你对体型、体重或者饮食比较关注，建议你使用本程序进行科学的饮食管理及帮助你更好地情绪调节。";
            } else {
                stepWord = "经过评估，你可能存在体重、体型或饮食方面的困扰，建议你使用本程序进行科学的饮食管理，帮助你更好地调节情绪，形成一个健康的饮食习惯和良好的生活方式，过上你想过的生活。";
            }
//            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.EAT_26);
//            if (userDailyStatistics != null){
//                String value = userDailyStatistics.getValue();

                // 后测
//                if (user.getIsScaleSecond() != null && user.getIsScaleSecond() == BooleanType.TRUE.getCode()){
//                    if (Integer.parseInt(value) >= 16){
//                        stepWord = "经过评估，你可能依然存在饮食方面的问题，建议你继续使用本程序进行科学的饮食行为管理。";
//                    } else {
//                        stepWord = "经过评估，你目前存在进食或者体型方面的风险较低，但是你依然可以继续使用本程序的技能！";
//                        canEnterMain = true;
//                        updateUserSecondDone(user);
//                    }
//                } else {
//                    if (Integer.parseInt(value) >= 16){
//                        stepWord = "经过评估，你可能存在进食或者体型方面的困扰，建议你使用本程序进行科学的饮食行为管理，来调节你的饮食状况，形成一个健康，合理的生活方式，过上你想过的生活。";
//                    } else {
//                        stepWord = "经过评估，你存在进食或者体型方面的风险较低！但是你依然可以浏览本程序内容，智慧饮食。";
//                        canEnterMain = true;
//                        updateUserSecondDone(user);
//                    }
//                }

//                if (Integer.parseInt(value) > 16){
//                    stepWord = "经过评估，你可能存在较严重的饮食方面的问题，建议咨询专业人士或医生对你的情况进一步了解，必要时可接受适当的治疗。还可至上海市精神卫生中心进食障碍诊治中心零陵路604号，建议门诊诊治。";
//                    canEnterMain = true;
//                    updateUserSecondDone(user);
//                } else {
//                    stepWord = "经过评估，你存在较高的罹患进食障碍的风险，建议你使用本程序进行科学的饮食行为管理，来调节你的饮食状况，形成一个健康，合理的生活方式，过上你想过的生活。";
//                }
//            }
        } else if (step == UserStepEnum.PHQ_9_RESULT.getNo()){
            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.PHQ_9);
            if (userDailyStatistics != null){
                String value = userDailyStatistics.getValue();
                if (Integer.parseInt(value) <= 4){
                    stepWord = "你的得分提示你最近没有抑郁症状，请继续保持";
                } else if (Integer.parseInt(value) <= 9){
                    stepWord = "你的得分提示你最近有轻微抑郁症状，建议适当调节";
                } else if (Integer.parseInt(value) <= 14){
                    stepWord = "你的得分提示你最近有轻度抑郁症状，建议适当调节或咨询专业人士";
                } else if (Integer.parseInt(value) <= 19){
                    stepWord = "你的得分提示你最近有中度的重性抑郁发，建议咨询专业人士或医生对你的情况进一步了解，必要时可接受适当的治疗";
                } else if (Integer.parseInt(value) >= 20){
                    stepWord = "你的得分提示你最近有重度的重性抑郁发作，建议咨询专业人士或医生对你的情况进一步了解，必要时可接受住院治疗";
                }
            }
        } else if (step == UserStepEnum.GAD_7_RESULT.getNo()){
            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.GAD_7);
            if (userDailyStatistics != null){
                String value = userDailyStatistics.getValue();
                if (Integer.parseInt(value) <= 4){
                    stepWord = "你的得分提示你最近没有焦虑症状，请继续保持";
                } else if (Integer.parseInt(value) <= 9){
                    stepWord = "你的得分提示你最近有轻度焦虑症状，建议适当调节或咨询专业人士";
                } else if (Integer.parseInt(value) <= 14){
                    stepWord = "你的得分提示你最近有中度焦虑症状，建议咨询专业人士或医生对你的情况进一步了解，必要时可接受适当的治疗";
                } else if (Integer.parseInt(value) <= 21){
                    stepWord = "你的得分提示你最近有重度焦虑症状，建议咨询专业人士或医生对你的情况进一步了解，必要时可接受住院治疗";
                }
            }
        } else if (step == UserStepEnum.SES_RESULT.getNo()){
            userDailyStatistics = userDailyStatisticsBLL.getLastest(userId, UserDailyStatisticsTypeEnum.SES);
            if (userDailyStatistics != null){
                String value = userDailyStatistics.getValue();
                if (Integer.parseInt(value) < 25){
                    stepWord = "你的得分提示你的自尊水平较低，可能对自我存在很多负面评价，可参考“不评价”科普文章。";
                } else if (Integer.parseInt(value) <= 32){
                    stepWord = "你的得分提示你的自尊水平为中等，加油，走在中道上。";
                } else if (Integer.parseInt(value) >= 33){
                    stepWord = "你的得分提示你的自尊水平较高，对自我接纳程度较好。";
                }
                canEnterMain = true;
                updateUserSecondDone(user);
            }
        }
        userExtraDTO.setStepWord(stepWord);
        userExtraDTO.setCanEnterMain(canEnterMain);
        return userExtraDTO;
    }

    /**
     * 后测量表更新成功状态
     * @param user
     * @return
     */
    private boolean updateUserSecondDone(User user){
        user = userService.selectById(user.getId());
        if (user.getIsScaleSecond() != null && user.getIsScaleSecond() == BooleanType.TRUE.getCode()){
//            user.setIsSecondDone(BooleanType.TRUE.getCode());
            user.setIsPeriodDone(BooleanType.TRUE.getCode());
            userService.updateById(user);
        }
        return true;
    }

    private boolean higher(int age, int gender, Float bmi){
        boolean isHigher = false;
        if (gender == 1){  // 男
            if ((age <= 8 && bmi >= 13.8) || (age == 9 && bmi >= 13.9) || (age == 10 && bmi >= 14.2) ||
                    (age == 11 && bmi >= 14.5) ||  (age == 12 && bmi >= 15.0) ||  (age == 13 && bmi >= 15.5) ||
                    (age == 14 && bmi >= 16.0) ||  (age == 15 && bmi >= 16.5) ||  (age == 16 && bmi >= 17.1) ||
                    (age == 17 && bmi >= 17.7) ||  (age >= 18 && bmi >= 18.5)){
                isHigher = true;
            }
        }
        if (gender == 2){  // 女
            if ((age <= 8 && bmi >= 13.5) || (age == 9 && bmi >= 13.8) || (age == 10 && bmi >= 14.0) ||
                    (age == 11 && bmi >= 14.4) ||  (age == 12 && bmi >= 14.8) ||  (age == 13 && bmi >= 15.3) ||
                    (age == 14 && bmi >= 15.8) ||  (age == 15 && bmi >= 16.3) ||  (age == 16 && bmi >= 16.8) ||
                    (age == 17 && bmi >= 17.2) ||  (age >= 18 && bmi >= 18.5)){
                isHigher = true;
            }
        }
        return isHigher;
    }
}
