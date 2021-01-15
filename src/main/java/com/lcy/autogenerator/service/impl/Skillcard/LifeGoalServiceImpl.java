package com.lcy.autogenerator.service.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoal;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.mapper.Skillcard.LifeGoalMapper;
import com.lcy.autogenerator.mapper.UserDailyStatisticsMapper;
import com.lcy.autogenerator.service.Skillcard.ILifeGoalService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.LifeGoalDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.params.business.Skillcard.LifeGoalParams;
import com.lcy.params.common.IDParams;
import com.lcy.params.common.IdPageParams;
import com.lcy.type.business.UserDailyStatisticsTypeEnum;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.MD5;
import com.lcy.util.common.ModelMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 存储用户的人生目标卡 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-01-12
 */
@Service
public class LifeGoalServiceImpl extends ServiceImpl<LifeGoalMapper, LifeGoal> implements ILifeGoalService {

    @Autowired
    @SuppressWarnings("all")
    private LifeGoalMapper lifeGoalMapper;

    @Autowired
    @SuppressWarnings("all")
    private UserDailyStatisticsMapper userDailyStatisticsMapper;


    @Override
    public boolean save(String userId,LifeGoalParams params) {
        LifeGoal lifeGoal = ModelMapperUtils.map(params, LifeGoal.class);
        String date = getDate();
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + System.currentTimeMillis());
        lifeGoal.setId(id);
        lifeGoal.setDate(date);
        lifeGoal.setUserId(userId);
//        现将用户的目标步骤等信息存入life-goal表中
        boolean flag = retBool(lifeGoalMapper.insert(lifeGoal));
//        成功插入后进行对用户使用人生目标卡的次数进行操作
        if(flag){
//            查找该用户今天有没有使用过
            UserDailyStatistics bean = lifeGoalMapper.getTodayUserDailyStatisticsById(userId);
            if(bean == null){
                UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
                userDailyStatistics.setId(id);
                userDailyStatistics.setUserId(userId);
                userDailyStatistics.setType(UserDailyStatisticsTypeEnum.LIFE_GOAL.getNo());
                userDailyStatistics.setCount(1L);
                userDailyStatistics.setCount2(0L);
                userDailyStatistics.setCount3(0L);
                userDailyStatistics.setDate(date);
                return retBool(userDailyStatisticsMapper.insert(userDailyStatistics));
            }else{
//                使用过的话更新原有的数值+1
                bean.setCount(bean.getCount() + 1);
                return retBool(userDailyStatisticsMapper.updateById(bean));
            }
        }else{
            throw new ServiceException("添加失败");
        }
    }

    @Override
//    更新貌似用不到
    public boolean update(String userId,LifeGoalParams params) {
        LifeGoal bean = ModelMapperUtils.map(params, LifeGoal.class);
        LifeGoal oldBean = lifeGoalMapper.selectById(bean.getId());
        if(oldBean == null){
            throw new ServiceException("获取不到更新对象");
        }
        if(StringUtils.isEmpty(bean.getTargetsJSON())){
            throw new ServiceException("5个目标不能为空");
        }
        if(StringUtils.isEmpty(bean.getTargetJSON())){
            throw new ServiceException("重要目标不能为空");
        }
        if(StringUtils.isEmpty(bean.getStepJSON())){
            throw new ServiceException("完成的步骤不能为空");
        }
        String date = getDate();
        bean.setDate(date);
        return retBool(lifeGoalMapper.updateById(bean));
    }

    @Override
    public boolean remove(String id) {
        LifeGoal lifeGoal = lifeGoalMapper.selectById(id);
        if(lifeGoal == null){
            return true;
        }
        if(!retBool(lifeGoalMapper.deleteById(id))){
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Override
    public LifeGoalDTO getById(String id) {
        LifeGoal lifeGoal = lifeGoalMapper.selectById(id);
        return tran(lifeGoal);
    }

    @Override
    public List<LifeGoalDTO> listUserLifeGoal(IDParams userId) {
        List<LifeGoal> lifeGoals = lifeGoalMapper.listUserLifeGoal(userId.getId());
        return tran(lifeGoals);
    }


    private String getDate(){
        long timeMillis = System.currentTimeMillis();
        return DateUtils.parseTimeToDate(timeMillis);
    }

    private List<LifeGoalDTO> tran(List<LifeGoal> lifeGoals){
        if(lifeGoals.size() == 0){
            return null;
        }
        List<LifeGoalDTO> lifeGoalDTOS = new ArrayList<>();
        for(LifeGoal lifeGoal : lifeGoals){
            lifeGoalDTOS.add(tran(lifeGoal));
        }
        return lifeGoalDTOS;
    }

    private LifeGoalDTO tran(LifeGoal lifeGoal){
        if(lifeGoal == null){
            return null;
        }
        return ModelMapperUtils.map(lifeGoal, LifeGoalDTO.class);
    }
}
