package com.lcy.autogenerator.service.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.EmotionRegulationCard;
import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.mapper.Skillcard.EmotionRegulationCardMapper;
import com.lcy.autogenerator.mapper.Skillcard.EmotionRegulationIssueMapper;
import com.lcy.autogenerator.mapper.UserDailyStatisticsMapper;
import com.lcy.autogenerator.service.Skillcard.IEmotionRegulationCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.EmotionRegulationCardDTO;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.params.business.Skillcard.EmotionRegulationCardParam;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.MD5;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 情绪调节卡 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-02-03
 */
@Service
public class EmotionRegulationCardServiceImpl extends ServiceImpl<EmotionRegulationCardMapper, EmotionRegulationCard> implements IEmotionRegulationCardService {

    @Autowired
    @SuppressWarnings("all")
    private EmotionRegulationCardMapper emotionRegulationCardMapper;

    @Autowired
    @SuppressWarnings("all")
    private EmotionRegulationIssueMapper emotionRegulationIssueMapper;

    @Autowired
    @SuppressWarnings("all")
    private UserDailyStatisticsMapper userDailyStatisticsMapper;

    @Override
    public boolean save(String userId,EmotionRegulationCardParam param) {
        EmotionRegulationCard emotionRegulationCard = tran(param);
        String date = getDate();
        String id = MD5.getMD5Code(param.getUserId() + InnoPlatformConstants.COMMA_EN + System.currentTimeMillis());
        emotionRegulationCard.setId(id);
        emotionRegulationCard.setDate(date);
        emotionRegulationCard.setUserId(userId);
        boolean flag = retBool(emotionRegulationCardMapper.insert(emotionRegulationCard));
        if (flag){
//            查找后台记录的数据，当天是否有使用过情绪调节卡
            UserDailyStatistics bean = emotionRegulationCardMapper.getTodayUserDailyStatisticsById(emotionRegulationCard.getUserId());
//            没有使用就创建新的数据
            if(bean == null){
                UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
                userDailyStatistics.setId(id);
                userDailyStatistics.setUserId(userId);
                userDailyStatistics.setType(UserDailyStatisticsTypeEnum.EMOTION_REGULATION_CARD.getNo());
                userDailyStatistics.setCount(1L);
                userDailyStatistics.setCount2(0L);
                userDailyStatistics.setCount3(0L);
                userDailyStatistics.setDate(date);
                return retBool(userDailyStatisticsMapper.insert(userDailyStatistics));
            }else{
//                如果使用过就在原有数据+1
                bean.setCount(bean.getCount() + 1);
                return retBool(userDailyStatisticsMapper.updateById(bean));
            }
        }else {
            throw new ServiceException("添加失败");
        }
    }

    @Override
    public boolean remove(String id) {
        EmotionRegulationCard bean = emotionRegulationCardMapper.selectById(id);
        if(bean == null){
            return true;
        }
        if(!retBool(emotionRegulationCardMapper.deleteById(id))){
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Override
    public boolean update(EmotionRegulationCardParam param) {
        EmotionRegulationCard bean = tran(param);
        EmotionRegulationCard oldBean = emotionRegulationCardMapper.selectById(bean.getId());
        if(oldBean == null){
            throw new ServiceException("获取不到更新对象");
        }
        if(StringUtils.isEmpty(bean.getUserAnswer())){
            throw new ServiceException("答案不能为空");
        }

        return retBool(emotionRegulationCardMapper.updateById(bean));
    }

    @Override
    public String getIssue() {
        return emotionRegulationIssueMapper.getIssue();
    }

    private EmotionRegulationCard tran(EmotionRegulationCardParam params){
        return ModelMapperUtils.map(params,EmotionRegulationCard.class);
    }

    private List<EmotionRegulationCardDTO> tran(List<EmotionRegulationCard> emotionRegulationCards){
        List<EmotionRegulationCardDTO> emotionRegulationCardDTOS  = new ArrayList<>();
        if(emotionRegulationCards.size() == 0){
            return emotionRegulationCardDTOS;
        }

        for(EmotionRegulationCard emotionRegulationCard : emotionRegulationCards){
            emotionRegulationCardDTOS.add(tran(emotionRegulationCard));
        }
        return emotionRegulationCardDTOS;
    }

    private EmotionRegulationCardDTO tran(EmotionRegulationCard emotionRegulationCard){

        if(emotionRegulationCard == null){
            return null;
        }

        return ModelMapperUtils.map(emotionRegulationCard,EmotionRegulationCardDTO.class);
    }

    private String getDate(){
        long timeMillis = System.currentTimeMillis();
        return DateUtils.parseTimeToDate(timeMillis);
    }

}
