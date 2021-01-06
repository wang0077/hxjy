package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.Attention;
import com.lcy.autogenerator.mapper.AttentionMapper;
import com.lcy.autogenerator.service.IArticleService;
import com.lcy.autogenerator.service.IAttentionService;
import com.lcy.bll.business.IAttentionBLL;
import com.lcy.bll.business.IMindfulnessBLL;
import com.lcy.bll.generalconfig.IArticleServiceBLL;
import com.lcy.contant.InnoPlatformConstants;
import com.lcy.dto.business.AttentionDTO;
import com.lcy.dto.business.AttentionDTO;
import com.lcy.dto.generalconfig.ArticleDTO;
import com.lcy.params.common.IDParams;
import com.lcy.type.business.AttentionOperTypeEnum;
import com.lcy.type.business.AttentionResourceTypeEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.MD5;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttentionBLL extends AbstractBO<Attention> implements IAttentionBLL {

    @Autowired
    AttentionMapper attentionMapper;

    @Autowired
    IAttentionService attentionService;

    @Autowired
    IMindfulnessBLL mindfulnessBLL;

    @Autowired
    IArticleServiceBLL articleServiceBLL;

    @Override
    public boolean save(String userId, AttentionOperTypeEnum operTypeEnum, AttentionResourceTypeEnum resourceTypeEnum, String resourceId, boolean flag, String toUserId) {
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + resourceId);

        Attention attention = new Attention();
        attention.setId(id);
        attention.setCreateTime(System.currentTimeMillis());
        attention.setFlag(flag ? BooleanType.TRUE.getCode() : BooleanType.FALSE.getCode());
        attention.setUserId(userId);
        attention.setResourceId(resourceId);
        attention.setOper(operTypeEnum.getNo());
        attention.setType(resourceTypeEnum.getNo());
        attention.setToUserId(toUserId);
        return attentionService.insertOrUpdate(attention);
    }

    @Override
    public boolean hasAttetion(String userId, String resourceId) {
        String id = MD5.getMD5Code(userId + InnoPlatformConstants.COMMA_EN + resourceId);
        Attention attention = get(id);
        return attention != null && attention.getFlag() == BooleanType.TRUE.getCode();
    }

    @Override
    public long countToUserAttetion(String toUserId){
        EntityWrapper<Attention> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("TO_USER_ID", toUserId);
        entityWrapper.eq("FLAG", BooleanType.TRUE.getCode());
        return attentionService.selectCount(entityWrapper);
    }

    @Override
    public long countResourceAttetion(String resourceId) {
        EntityWrapper<Attention> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("RESOURCE_ID", resourceId);
        entityWrapper.eq("FLAG", BooleanType.TRUE.getCode());
        return attentionService.selectCount(entityWrapper);
    }

    @Override
    public List<AttentionDTO> list(String userId, AttentionResourceTypeEnum resourceTypeEnum, Long lastDate, int pageSize) {

        EntityWrapper<Attention> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("USER_ID", userId);
        entityWrapper.eq("TYPE", resourceTypeEnum.getNo());
        entityWrapper.eq("FLAG", BooleanType.TRUE.getCode());

        if (lastDate != null && lastDate != 0){
            entityWrapper.lt("CREATE_TIME", lastDate);
        }

        entityWrapper.orderBy("CREATE_TIME", false);
        List<Attention> attentions = attentionService.selectList(entityWrapper);
        List<AttentionDTO> dtoList = new ArrayList<>();
        for (Attention record : attentions) {
            dtoList.add(trans(record));
        }
        return dtoList;
    }

    private AttentionDTO trans(Attention record) {
        AttentionDTO dto = ModelMapperUtils.map(record, AttentionDTO.class);
        if (dto.getType() == AttentionResourceTypeEnum.COLLECT_ARTICLE.getNo()){

            ArticleDTO article = articleServiceBLL.get(new IDParams(dto.getResourceId()));
            if (article != null){
                String coverPicId = article.getCoverPicId();
                if (StringUtils.isNotEmpty(coverPicId)) {

                    IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();
                    String firstFilePath = fileSystemInstance.getFirstFilePath(coverPicId);
                    if (StringUtils.isEmpty(firstFilePath)) {
                        firstFilePath = fileSystemInstance.getFilePathById(coverPicId);
                    }
                    article.setCoverPicUrl(firstFilePath);
                }
            }
            dto.setResourceInfo(article);
        } else if (dto.getType() == AttentionResourceTypeEnum.COLLECT_MINDFULNESS.getNo()){
            dto.setResourceInfo(mindfulnessBLL.getDTO(dto.getResourceId()));
        }

        dto.setLastdate(record.getCreateTime());
        dto.setShowTime(DateUtils.parseTimeToDateStr(record.getCreateTime()));
        return dto;
    }

    @Override
    public Attention get(String id) {
        return attentionService.selectById(id);
    }

    @Override
    public String save(String operUserId, Attention bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, Attention bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(Attention bean) {

    }

    @Override
    public void removeCache(Attention bean) {

    }
}
