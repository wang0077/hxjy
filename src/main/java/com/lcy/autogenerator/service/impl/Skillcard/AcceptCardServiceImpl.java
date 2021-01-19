package com.lcy.autogenerator.service.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.AcceptCard;
import com.lcy.autogenerator.entity.Skillcard.ProsConsCard;
import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.autogenerator.mapper.Skillcard.AcceptCardMapper;
import com.lcy.autogenerator.mapper.UserDailyStatisticsMapper;
import com.lcy.autogenerator.service.Skillcard.IAcceptCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.AcceptCardDTO;
import com.lcy.dto.business.Skillcard.ProsConsCardDTO;
import com.lcy.params.business.Skillcard.AcceptCardParam;
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
 * 全然接受卡 服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-01-19
 */
@Service
public class AcceptCardServiceImpl extends ServiceImpl<AcceptCardMapper, AcceptCard> implements IAcceptCardService {

    @Autowired
    @SuppressWarnings("all")
    private AcceptCardMapper acceptCardMapper;

    @Autowired
    @SuppressWarnings("all")
    private UserDailyStatisticsMapper userDailyStatisticsMapper;

    @Override
    public boolean save(String userId,AcceptCardParam param) {
        AcceptCard acceptCard = tran(param);
        String date = getDate();
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + System.currentTimeMillis());
        acceptCard.setId(id);
        acceptCard.setDate(date);
        acceptCard.setUserId(userId);
        boolean flag = retBool(acceptCardMapper.insert(acceptCard));
        if(flag){
            //            查找该用户今天有没有使用过
            UserDailyStatistics bean = acceptCardMapper.getTodayUserDailyStatisticsById(userId);
            if(bean == null){
                UserDailyStatistics userDailyStatistics = new UserDailyStatistics();
                userDailyStatistics.setId(id);
                userDailyStatistics.setUserId(userId);
                userDailyStatistics.setType(UserDailyStatisticsTypeEnum.ACCEPT_CARD.getNo());
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
    public boolean update(AcceptCardParam param) {
        AcceptCard bean = tran(param);
        AcceptCard oldBean = acceptCardMapper.selectById(param.getId());
        if(oldBean == null){
            throw new ServiceException("获取不到更新对象");
        }
        if(StringUtils.isEmpty(bean.getFact())){
            throw new ServiceException("需要接受的事实不能为空");
        }
        String date = getDate();
        bean.setDate(date);
        return retBool(acceptCardMapper.updateById(bean));
    }

    @Override
    public boolean remove(String id) {
        AcceptCard bean = acceptCardMapper.selectById(id);
        if(bean == null){
            return true;
        }
        if(!retBool(acceptCardMapper.deleteById(id))){
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Override
    public List<AcceptCardDTO> listByUserId(String userId) {
        List<AcceptCard> acceptCards = acceptCardMapper.listByUserId(userId);
        return tran(acceptCards);
    }

    @Override
    public AcceptCardDTO getById(String id) {
        AcceptCard acceptCard = acceptCardMapper.selectById(id);
        return tran(acceptCard);
    }

    private String getDate(){
        long timeMillis = System.currentTimeMillis();
        return DateUtils.parseTimeToDate(timeMillis);
    }

    private AcceptCard tran(AcceptCardParam param){
        return ModelMapperUtils.map(param,AcceptCard.class);
    }

    private List<AcceptCardDTO> tran(List<AcceptCard> acceptCards){
        List<AcceptCardDTO> acceptCardDTOS = new ArrayList<>();
        if(acceptCards.size() == 0){
            return acceptCardDTOS;
        }
        for (AcceptCard acceptCard : acceptCards){
            acceptCardDTOS.add(tran(acceptCard));
        }
        return acceptCardDTOS;
    }


    private AcceptCardDTO tran(AcceptCard acceptCard){
        if(acceptCard == null){
            return null;
        }
        return ModelMapperUtils.map(acceptCard,AcceptCardDTO.class);
    }
}
