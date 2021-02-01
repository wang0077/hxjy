package com.lcy.autogenerator.service.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.BehaviorChainCard;
import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.mapper.Skillcard.BehaviorChainCardMapper;
import com.lcy.autogenerator.mapper.UserDailyStatisticsMapper;
import com.lcy.autogenerator.service.Skillcard.IBehaviorChainCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.BehaviorChainCardDTO;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.params.business.Skillcard.BehaviorChainCardParam;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.MD5;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-02-01
 */
@Service
public class BehaviorChainCardServiceImpl extends ServiceImpl<BehaviorChainCardMapper, BehaviorChainCard> implements IBehaviorChainCardService {

    @Autowired
    @SuppressWarnings("all")
    private BehaviorChainCardMapper behaviorChainCardMapper;

    @Autowired
    @SuppressWarnings("all")
    private UserDailyStatisticsMapper userDailyStatisticsMapper;


    @Override
    public boolean save(String userId,BehaviorChainCardParam param) {
        BehaviorChainCard behaviorChainCard = tran(param);
        String date = getDate();
        String id = MD5.getMD5Code(param.getUserId() + InnoPlatformConstants.COMMA_EN + System.currentTimeMillis());
        behaviorChainCard.setId(id);
        behaviorChainCard.setUserId(userId);
        behaviorChainCard.setDATE(date);
        boolean flag = retBool(behaviorChainCardMapper.insert(behaviorChainCard));
        if(flag){
            UserDailyStatistics bean = behaviorChainCardMapper.getTodayUserDailyStatisticsById(userId);
            if(bean == null){
                UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
                userDailyStatistics.setId(id);
                userDailyStatistics.setUserId(userId);
                userDailyStatistics.setType(UserDailyStatisticsTypeEnum.BEHAVIOR_CHAIN_CARD.getNo());
                userDailyStatistics.setCount(1L);
                userDailyStatistics.setCount2(0L);
                userDailyStatistics.setCount3(0L);
                userDailyStatistics.setDate(date);
                return retBool(userDailyStatisticsMapper.insert(userDailyStatistics));
            }else{
                bean.setCount(bean.getCount() + 1);
                return retBool(userDailyStatisticsMapper.updateById(bean));
            }
        }else{
            throw new ServiceException("添加失败");
        }
    }

    @Override
    public boolean remove(String id) {
        BehaviorChainCard bean = behaviorChainCardMapper.selectById(id);
        if(bean == null){
            return true;
        }
        if(!retBool(behaviorChainCardMapper.deleteById(id))){
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Override
    public List<BehaviorChainCardDTO> listByUserId(String userId) {
        List<BehaviorChainCard> behaviorChainCards = behaviorChainCardMapper.listByUserId(userId);
        return tran(behaviorChainCards);
    }


    @Override
    public boolean update(BehaviorChainCardParam param) {
        BehaviorChainCard bean = tran(param);
        BehaviorChainCard oldBean = behaviorChainCardMapper.selectById(param.getId());
        if(oldBean == null){
            throw new ServiceException("获取不到更新对象");
        }
        if(StringUtils.isEmpty(bean.getProblem())){
            throw new ServiceException("具体的问题行为不能为空");
        }
        if(StringUtils.isEmpty(bean.getInducement())){
            throw new ServiceException("具体诱发事件不能为空");
        }
        if(StringUtils.isEmpty(bean.getFactor())){
            throw new ServiceException("易感因素不能为空");
        }
        if(StringUtils.isEmpty(bean.getEventChainJSON())){
            throw new ServiceException("事件链不能为空");
        }
        if(StringUtils.isEmpty(bean.getConsequenceJSON())){
            throw new ServiceException("问题行为的后果不能为空");
        }
        if(StringUtils.isEmpty(bean.getSkillJSON())){
            throw new ServiceException("可以替代事件链中问题链接的技能行为不能为空");
        }
        if(StringUtils.isEmpty(bean.getPlan())){
            throw new ServiceException("修复伤害以及下制定预防计划不能为空");
        }
        return retBool(behaviorChainCardMapper.updateById(bean));
    }

    private BehaviorChainCard tran(BehaviorChainCardParam param){
        return ModelMapperUtils.map(param,BehaviorChainCard.class);
    }

    private List<BehaviorChainCardDTO> tran(List<BehaviorChainCard> behaviorChainCards){
        List<BehaviorChainCardDTO> behaviorChainCardDTOS  = new ArrayList<>();
        if(behaviorChainCards.size() == 0){
            return behaviorChainCardDTOS;
        }

        for(BehaviorChainCard behaviorChainCard : behaviorChainCards){
            behaviorChainCardDTOS.add(tran(behaviorChainCard));
        }
        return behaviorChainCardDTOS;
    }

    private BehaviorChainCardDTO tran(BehaviorChainCard behaviorChainCard){

        if(behaviorChainCard == null){
            return null;
        }

        return ModelMapperUtils.map(behaviorChainCard,BehaviorChainCardDTO.class);
    }

    private String getDate(){
        long timeMillis = System.currentTimeMillis();
        return DateUtils.parseTimeToDate(timeMillis);
    }
}
