package com.lcy.autogenerator.service.impl.Skillcard;

import com.lcy.autogenerator.entity.Skillcard.LifeGoalCard;
import com.lcy.autogenerator.mapper.Skillcard.LifeGoalCardMapper;
import com.lcy.autogenerator.service.Skillcard.ILifeGoalCardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.Skillcard.LifeGoalCardDTO;
import com.lcy.params.business.Skillcard.LifeGoalCardParams;
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
 * @author wangsiyuan
 * @since 2021-01-07
 */
@Service
public class LifeGoalCardServiceImpl extends ServiceImpl<LifeGoalCardMapper, LifeGoalCard> implements ILifeGoalCardService {

    @Autowired
    @SuppressWarnings("all")
    private LifeGoalCardMapper lifeGoalCardMapper;

    @Override
    public boolean save(LifeGoalCardParams params){
        LifeGoalCard lifeGoalCard = ModelMapperUtils.map(params,LifeGoalCard.class);
        return retBool(lifeGoalCardMapper.insert(lifeGoalCard));
    }

    @Override
    public boolean remove(String id) {

        LifeGoalCard bean = lifeGoalCardMapper.selectById(id);
        if(bean == null){
            return true;
        }
        Integer ID = Integer.parseInt(id);
        if(!retBool(lifeGoalCardMapper.deleteById(ID))){
            throw new ServiceException("删除失败");
        }

        return true;
    }

    @Override
    public List<LifeGoalCardDTO> listLifeGoalCardAll() {
        List<LifeGoalCard> lifeGoalCards = lifeGoalCardMapper.listAll();
        return tran(lifeGoalCards);
    }

    @Override
    public List<LifeGoalCardDTO> listLifeGoalCardById(List<Integer> ids) {
        List<LifeGoalCard> lifeGoalCards = lifeGoalCardMapper.selectBatchIds(ids);
        return tran(lifeGoalCards);
    }

    @Override
    public LifeGoalCardDTO getLifeGoalCardById(Integer id) {
        LifeGoalCard lifeGoalCard = lifeGoalCardMapper.selectById(id);
        return tran(lifeGoalCard);
    }


    @Override
    public boolean update(LifeGoalCardParams params) {
        LifeGoalCard bean = ModelMapperUtils.map(params,LifeGoalCard.class);
        LifeGoalCard oldBean = lifeGoalCardMapper.selectById(bean.getId());
        if(oldBean == null){
            throw new ServiceException("获取不到更新对象");
        }
        if(StringUtils.isEmpty(bean.getCardName())){
            throw new ServiceException("卡片名称不能为空");
        }
        if(StringUtils.isEmpty(bean.getCardDescribe())){
            throw new ServiceException("卡片描述不能为空");
        }
        boolean isSuccess = retBool(lifeGoalCardMapper.updateById(bean));

        return isSuccess;
    }


    private List<LifeGoalCardDTO> tran(List<LifeGoalCard> lifeGoalCards){
        List<LifeGoalCardDTO> lifeGoalCardDTOS = new ArrayList<>();
        if(lifeGoalCards.size() == 0){
            return lifeGoalCardDTOS;
        }
        for (LifeGoalCard lifeGoalCard : lifeGoalCards){
            lifeGoalCardDTOS.add(tran(lifeGoalCard));
        }
        return lifeGoalCardDTOS;
    }

    private LifeGoalCardDTO tran(LifeGoalCard lifeGoalCard){
        if(lifeGoalCard == null){
            return null;
        }
        return ModelMapperUtils.map(lifeGoalCard,LifeGoalCardDTO.class);
    }

}
