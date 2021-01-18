package com.lcy.autogenerator.service.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoal;
import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.mapper.Skillcard.ProsConsCardMapper;
import com.lcy.autogenerator.mapper.UserDailyStatisticsMapper;
import com.lcy.autogenerator.service.Skillcard.IProsConsCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.bll.business.IUserDailyStatisticsBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.params.business.Skillcard.ProsConsCardParams;
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
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-01-16
 */
@Service
public class ProsConsCardServiceImpl extends ServiceImpl<ProsConsCardMapper, ProsConsCard> implements IProsConsCardService {

    @Autowired
    @SuppressWarnings("all")
    private ProsConsCardMapper prosConsCardMapper;

    @Autowired
    @SuppressWarnings("all")
    private UserDailyStatisticsMapper userDailyStatisticsMapper;


    @Override
    public boolean save(String userId,ProsConsCardParams params) {
        ProsConsCard prosConsCard = tran(params);
        String date = getDate();
        String id = MD5.getMD5Code(params.getUserId() + InnoPlatformConstants.COMMA_EN + System.currentTimeMillis());
        prosConsCard.setId(id);
        prosConsCard.setDate(date);
        prosConsCard.setUserId(userId);
//        将数据插入pros_cons_card表中
        boolean flag = retBool(prosConsCardMapper.insert(prosConsCard));
        if (flag){
//            查找后台记录的数据，当天是否有使用过利弊分析卡
            UserDailyStatistics bean = prosConsCardMapper.getTodayUserDailyStatisticsById(prosConsCard.getUserId());
//            没有使用就创建新的数据
            if(bean == null){
                UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
                userDailyStatistics.setId(id);
                userDailyStatistics.setUserId(userId);
                userDailyStatistics.setType(UserDailyStatisticsTypeEnum.PROS_CONS.getNo());
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
        ProsConsCard bean = prosConsCardMapper.selectById(id);

        if(bean == null){
            return true;
        }

        if(!retBool(prosConsCardMapper.deleteById(id))){
            throw new ServiceException("删除失败");
        }

        return true;
    }

    @Override
    public boolean update(ProsConsCardParams params) {
        ProsConsCard bean = tran(params);
        ProsConsCard oldBean = prosConsCardMapper.selectById(params.getId());
        if(oldBean == null){
            throw new ServiceException("获取不到更新对象");
        }
        if(StringUtils.isEmpty(bean.getTarget())){
            throw new ServiceException("目标不能为空");
        }
        if(StringUtils.isEmpty(bean.getObeyJSON())){
            throw new ServiceException("按照危机冲动所行事不能为空");
        }
        if(StringUtils.isEmpty(bean.getViolateJSON())){
            throw new ServiceException("消除危机冲动所行事不能为空");

        }

        return retBool(prosConsCardMapper.updateById(bean));
    }

    @Override
    public ProsConsCardDTO getById(String id) {
        ProsConsCard prosConsCard = prosConsCardMapper.selectById(id);
        return tran(prosConsCard);
    }

    @Override
    public List<ProsConsCardDTO> listByUserId(String userId) {
        List<ProsConsCard> prosConsCards = prosConsCardMapper.listByUserId(userId);
        return tran(prosConsCards);
    }


    private ProsConsCard tran(ProsConsCardParams params){
        return ModelMapperUtils.map(params,ProsConsCard.class);
    }

    private List<ProsConsCardDTO> tran(List<ProsConsCard> prosConsCards){
        List<ProsConsCardDTO> prosConsCardDTOS  = new ArrayList<>();
        if(prosConsCards.size() == 0){
            return prosConsCardDTOS;
        }

        for(ProsConsCard prosConsCard : prosConsCards){
            prosConsCardDTOS.add(tran(prosConsCard));
        }
        return prosConsCardDTOS;
    }

    private ProsConsCardDTO tran(ProsConsCard prosConsCard){

        if(prosConsCard == null){
            return null;
        }

        return ModelMapperUtils.map(prosConsCard,ProsConsCardDTO.class);
    }

    private String getDate(){
        long timeMillis = System.currentTimeMillis();
        return DateUtils.parseTimeToDate(timeMillis);
    }
}
