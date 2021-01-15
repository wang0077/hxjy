package com.lcy.bll.business.impl;

import com.lcy.autogenerator.entity.ClockInRecord;
import com.lcy.autogenerator.entity.DietDiary;
import com.lcy.autogenerator.mapper.DietDiaryMapper;
import com.lcy.autogenerator.service.IDietDiaryService;
import com.lcy.bll.business.IClockInRecordBLL;
import com.lcy.bll.business.IDietDiaryBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.DietDiaryDTO;
import com.lcy.dto.business.DietDiarySaveDTO;
import com.lcy.dto.business.MealDTO;
import com.lcy.params.business.DietDiaryParams;
import com.lcy.type.business.ClockInRecordTypeEnum;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DietDiaryBLL extends AbstractBO<DietDiary> implements IDietDiaryBLL {

    @Autowired
    IDietDiaryService dietDiaryService;

    @Autowired
    DietDiaryMapper dietDiaryMapper;

    @Autowired
    IClockInRecordBLL clockInRecordBLL;

    @Override
    public DietDiary get(String id) {
        return dietDiaryService.selectById(id);
    }

    @Override
    public String save(String operUserId, DietDiary bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, DietDiary bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(DietDiary bean) {

    }

    @Override
    public void removeCache(DietDiary bean) {

    }

    @Override
    @Transactional
    public DietDiarySaveDTO saveOrUpdate(String userId, DietDiaryParams dietDiaryParams) {

        long timeMillis = System.currentTimeMillis();
        String date = DateUtils.parseTimeToDate(timeMillis);
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + date);

        DietDiary dietDiary = get(id);
        if (dietDiary == null){
            dietDiary = ModelMapperUtils.map(dietDiaryParams, DietDiary.class);
            dietDiary.setUserId(userId);
            dietDiary.setDate(date);
            dietDiary.setId(id);
            dietDiary.setCreateTime(timeMillis);
        } else {
            dietDiary = ModelMapperUtils.map(dietDiaryParams, DietDiary.class);
            dietDiary.setId(id);
            dietDiary.setUpdateTime(timeMillis);
        }

        boolean flag = dietDiaryService.insertOrUpdate(dietDiary);
        if (flag){
            dietDiary = get(id);
            // 暴食冲动次数
            long gluttonyImpulseTimes = 0;
            // 暴食次数
            long gluttonyTimes = 0;


            // 催吐次数
            long emeticTimes = 0;


            int configItemCount = 0;
            String percentValue = "";
            DietDiaryDTO dietDiaryDTO = tran(dietDiary);
            MealDTO breakfastMealDTO = dietDiaryDTO.getBreakfastMealDTO();
            if (breakfastMealDTO != null){
                gluttonyImpulseTimes += breakfastMealDTO.getGluttonyImpulseTimes() == null ? 0 : breakfastMealDTO.getGluttonyImpulseTimes();
                gluttonyTimes += breakfastMealDTO.getGluttonyTimes() == null ? 0 : breakfastMealDTO.getGluttonyTimes();
                emeticTimes += breakfastMealDTO.getEmeticTimes() == null ? 0 : breakfastMealDTO.getEmeticTimes();
                configItemCount += breakfastMealDTO.getConfigItemCount();
            }
            MealDTO lunchMealDTO = dietDiaryDTO.getLunchMealDTO();
            if (lunchMealDTO != null){
                gluttonyImpulseTimes += lunchMealDTO.getGluttonyImpulseTimes() == null ? 0 : lunchMealDTO.getGluttonyImpulseTimes();
                gluttonyTimes += lunchMealDTO.getGluttonyTimes() == null ? 0 : lunchMealDTO.getGluttonyTimes();
                emeticTimes += lunchMealDTO.getEmeticTimes() == null ? 0 : lunchMealDTO.getEmeticTimes();
                configItemCount += lunchMealDTO.getConfigItemCount();
            }
            MealDTO dinnerMealDTO = dietDiaryDTO.getDinnerMealDTO();
            if (dinnerMealDTO != null){
                gluttonyImpulseTimes += dinnerMealDTO.getGluttonyImpulseTimes() == null ? 0 : dinnerMealDTO.getGluttonyImpulseTimes();
                gluttonyTimes += dinnerMealDTO.getGluttonyTimes() == null ? 0 : dinnerMealDTO.getGluttonyTimes();
                emeticTimes += dinnerMealDTO.getEmeticTimes() == null ? 0 : dinnerMealDTO.getEmeticTimes();
                configItemCount += dinnerMealDTO.getConfigItemCount();
            }
//新添加部分 --------------------------把新的其他时间进食的数据加进去-------------------------------------
            MealDTO otherTimeEatDTO = dietDiaryDTO.getOtherTimeEatDTO();
            if(otherTimeEatDTO != null){
                gluttonyImpulseTimes += otherTimeEatDTO.getGluttonyImpulseTimes() == null ? 0 : otherTimeEatDTO.getGluttonyImpulseTimes();
                gluttonyTimes += otherTimeEatDTO.getGluttonyTimes() == null ? 0 : otherTimeEatDTO.getGluttonyTimes();
                emeticTimes += otherTimeEatDTO.getEmeticTimes() == null ? 0 : otherTimeEatDTO.getEmeticTimes();
                configItemCount += otherTimeEatDTO.getConfigItemCount();
            }
//--------------------------------------------------------------------------------------------------

//新添加部分 ----------------------------------------把原本的12改成20---------------------------------------------------------
            percentValue = DecimalFormatUtils.floatFormat(configItemCount/20f * 100) + "%";
//                  修改这个方法，把emeticTimes催吐的数据也传进去
            flag = clockInRecordBLL.dietDiaryClockIn(userId, date, false, gluttonyTimes, gluttonyImpulseTimes,emeticTimes, percentValue);
// ------------------------------------------------------------------------------------------------------------
        }
        DietDiarySaveDTO dto = new DietDiarySaveDTO();
        dto.setSuccess(flag);
        return dto;
    }

    @Override
    public DietDiarySaveDTO clockIn(String userId) {

        long timeMillis = System.currentTimeMillis();
        String date = DateUtils.parseTimeToDate(timeMillis);
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + date);

        DietDiary dietDiary = get(id);

        // 是否三餐都配置
        boolean isAllMeanConfig = false;
        if (dietDiary != null && StringUtils.isNotEmpty(dietDiary.getBreakfastJson()) && StringUtils.isNotEmpty(dietDiary.getLunchJson())
                && StringUtils.isNotEmpty(dietDiary.getDinnerJson())){
            isAllMeanConfig = true;
        }

        if (!isAllMeanConfig){
            throw new ServiceException("三餐都设置才能打卡");
        }

        // 暴食冲动次数
        long gluttonyImpulseTimes = 0;
        // 暴食次数
        long gluttonyTimes = 0;
        // 催吐次数
        long emeticTimes = 0;
        int configItemCount = 0;
        String percentValue = "";
        boolean canFeedback = true;
        Integer feedbackCode = null;
        DietDiaryDTO dietDiaryDTO = tran(dietDiary);
        MealDTO breakfastMealDTO = dietDiaryDTO.getBreakfastMealDTO();
        if (breakfastMealDTO != null){
            gluttonyImpulseTimes += breakfastMealDTO.getGluttonyImpulseTimes() == null ? 0 : breakfastMealDTO.getGluttonyImpulseTimes();
            gluttonyTimes += breakfastMealDTO.getGluttonyTimes() == null ? 0 : breakfastMealDTO.getGluttonyTimes();
            emeticTimes += breakfastMealDTO.getEmeticTimes() == null ? 0 : breakfastMealDTO.getEmeticTimes();
            configItemCount += breakfastMealDTO.getConfigItemCount();
        } else {
            canFeedback = false;
        }
        MealDTO lunchMealDTO = dietDiaryDTO.getLunchMealDTO();
        if (lunchMealDTO != null){
            gluttonyImpulseTimes += lunchMealDTO.getGluttonyImpulseTimes() == null ? 0 : lunchMealDTO.getGluttonyImpulseTimes();
            gluttonyTimes += lunchMealDTO.getGluttonyTimes() == null ? 0 : lunchMealDTO.getGluttonyTimes();
            emeticTimes += lunchMealDTO.getEmeticTimes() == null ? 0 : lunchMealDTO.getEmeticTimes();
            configItemCount += lunchMealDTO.getConfigItemCount();
        } else {
            canFeedback = false;
        }
        MealDTO dinnerMealDTO = dietDiaryDTO.getDinnerMealDTO();
        if (dinnerMealDTO != null){
            gluttonyImpulseTimes += dinnerMealDTO.getGluttonyImpulseTimes() == null ? 0 : dinnerMealDTO.getGluttonyImpulseTimes();
            gluttonyTimes += dinnerMealDTO.getGluttonyTimes() == null ? 0 : dinnerMealDTO.getGluttonyTimes();
            emeticTimes += dinnerMealDTO.getEmeticTimes() == null ? 0 : dinnerMealDTO.getEmeticTimes();
            configItemCount += dinnerMealDTO.getConfigItemCount();
        } else {
            canFeedback = false;
        }

//-------------------------这一部分找不到这个反馈码的意义--------------------------------
        if (canFeedback){
            if (gluttonyTimes > 0 && gluttonyImpulseTimes > 0){
                feedbackCode = 1;
            } else if (gluttonyTimes > 0 && gluttonyImpulseTimes == 0){
                feedbackCode = 2;
            } else if (gluttonyTimes == 0 && gluttonyImpulseTimes > 0){
                feedbackCode = 3;
            } else if (gluttonyTimes == 0 && gluttonyImpulseTimes == 0){
                feedbackCode = 4;
            }
        }
//  ----------------------------------------------------------------------------------
        percentValue = DecimalFormatUtils.floatFormat(configItemCount/20f * 100) + "%";

        boolean flag = clockInRecordBLL.dietDiaryClockIn(userId, date, true, gluttonyTimes, gluttonyImpulseTimes,emeticTimes, percentValue);
        DietDiarySaveDTO dto = new DietDiarySaveDTO();
        dto.setSuccess(flag);
        dto.setFeedbackCode(feedbackCode);
        return dto;
    }

    @Override
    public DietDiaryDTO getTodayDietDiaryDTO(String userId) {
        long timeMillis = System.currentTimeMillis();
        String date = DateUtils.parseTimeToDate(timeMillis);
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + date);

        DietDiary dietDiary = get(id);
        return tran(dietDiary);
    }

    private DietDiaryDTO tran(DietDiary dietDiary){
        if (dietDiary == null){
            return null;
        }

        DietDiaryDTO dto = ModelMapperUtils.map(dietDiary, DietDiaryDTO.class);

        if (StringUtils.isNotEmpty(dietDiary.getBreakfastJson())){
            dto.setBreakfastMealDTO(GsonUtils.jsonToBean(dietDiary.getBreakfastJson(), MealDTO.class));
        }
        if (StringUtils.isNotEmpty(dietDiary.getLunchJson())){
            dto.setLunchMealDTO(GsonUtils.jsonToBean(dietDiary.getLunchJson(), MealDTO.class));
        }
        if (StringUtils.isNotEmpty(dietDiary.getDinnerJson())){
            dto.setDinnerMealDTO(GsonUtils.jsonToBean(dietDiary.getDinnerJson(), MealDTO.class));
        }
// 新添加部分 --------------------------转换的时候把其他时间进食加进去---------------------------------------
        if(StringUtils.isNotEmpty(dietDiary.getOtherTimeEatJson())){
            dto.setOtherTimeEatDTO(GsonUtils.jsonToBean(dietDiary.getOtherTimeEatJson(),MealDTO.class));
        }
//---------------------------------------------------------------------------------------------------

        boolean hasClockIn = false;

        String date = DateUtils.parseTimeToDate(System.currentTimeMillis());
        ClockInRecord hasClockInRecord = clockInRecordBLL.get(dietDiary.getUserId(), ClockInRecordTypeEnum.DIET_DAIRY, date);

        // 饮食日记,一天三餐全部保存打卡过了
        if (hasClockInRecord != null){
            hasClockIn = true;
        }
        dto.setHasClockIn(hasClockIn);
        return dto;
    }
}
