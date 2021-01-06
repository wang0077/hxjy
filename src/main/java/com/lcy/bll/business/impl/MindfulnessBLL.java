package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.innochina.platform.clientpay.utils.PriceUtils;
import com.lcy.autogenerator.entity.Mindfulness;
import com.lcy.autogenerator.mapper.MindfulnessMapper;
import com.lcy.autogenerator.service.IMindfulnessService;
import com.lcy.bll.business.IAttentionBLL;
import com.lcy.bll.business.IMindfulnessBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.MindfulnessDTO;
import com.lcy.dto.common.PageResult;
import com.lcy.params.business.MindfulnessParams;
import com.lcy.type.business.MindfulnessTypeEnum;
import com.lcy.type.business.OnOffStatusEnum;
import com.lcy.type.common.BooleanType;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import com.lcy.util.file.FileSystemFactory;
import com.lcy.util.file.IFileSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class MindfulnessBLL extends AbstractBO<Mindfulness> implements IMindfulnessBLL {
    
    @Autowired
    IMindfulnessService mindfulnessService;

    @Autowired
    MindfulnessMapper mindfulnessMapper;

    @Autowired
    IAttentionBLL attentionBLL;
    
    @Override
    public boolean save(String operUserId, MindfulnessParams mindfulnessParams) {
        Mindfulness mindfulness = ModelMapperUtils.map(mindfulnessParams, Mindfulness.class);
        mindfulness.setType(mindfulnessParams.getMindfulnessType());
        mindfulness.setId(UUIDGenerator.getUUID());
        mindfulness.setCreateOperatorId(operUserId);
        mindfulness.setCreateTime(System.currentTimeMillis());
        mindfulness.setIsDeleted(BooleanType.FALSE.getCode());
        if (mindfulness.getStatus() == null){
            mindfulness.setStatus(OnOffStatusEnum.OFF_SALE.getNo());
        }

        if (mindfulness.getType() == null){
            mindfulness.setType(MindfulnessTypeEnum.MUSIC.getNo());
        }

        Long maxSort = mindfulnessMapper.getMaxSort(null);
        if (maxSort == null){
            mindfulness.setSort(1L);
        }else{
            mindfulness.setSort(maxSort + 1);
        }

        return mindfulnessService.insert(mindfulness);
    }

    @Override
    public boolean update(String operUserId, MindfulnessParams mindfulnessParams) {
        Mindfulness mindfulness = ModelMapperUtils.map(mindfulnessParams, Mindfulness.class);
        mindfulness.setType(mindfulnessParams.getMindfulnessType());
        mindfulness.setUpdateOperatorId(operUserId);
        mindfulness.setUpdateTime(System.currentTimeMillis());
        
        return mindfulnessService.updateById(mindfulness);
    }

    @Override
    public PageResult<MindfulnessDTO> page(String operUserId, LinkedHashMap<String, Object> filterMap, int pageNo, int pageSize) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (filterMap != null){
            if (filterMap.containsKey("beginTime")){
                hashMap.put("beginTime", filterMap.get("beginTime"));
            }
            if (filterMap.containsKey("endTime")){
                hashMap.put("endTime", filterMap.get("endTime"));
            }
            if (filterMap.containsKey("status") && (int)filterMap.get("status") != 0){
                hashMap.put("status", filterMap.get("status"));
            }
            if (filterMap.containsKey("nameKeyword")){
                hashMap.put("nameKeyword", filterMap.get("nameKeyword"));
            }
        }
        hashMap.put("offset", (pageNo - 1) * pageSize);
        hashMap.put("pageSize", pageSize);
        int countListOperation = mindfulnessMapper.countListOperation(hashMap);
        List<Mindfulness> mindfulnessList = mindfulnessMapper.listOperation(hashMap);
        List<MindfulnessDTO> mindfulnessDtoList = new ArrayList<>();
        if (mindfulnessList != null){
            for (Mindfulness mindfulness : mindfulnessList) {
                mindfulnessDtoList.add(trans(null, mindfulness));
            }
        }

        PageResult<MindfulnessDTO> result = new PageResult<>();
        result.setTotal(countListOperation);
        result.setList(mindfulnessDtoList);
        result.setPageSize(pageSize);
        result.setCurPage(pageNo);

        return result;
    }

    @Override
    public MindfulnessDTO getDTO(String id) {
        Mindfulness mindfulness = get(id);
        if (mindfulness == null){
            return null;
        }

        return trans(null, mindfulness);
    }

    private MindfulnessDTO trans(String operUserId, Mindfulness mindfulness){

        MindfulnessDTO dto = ModelMapperUtils.map(mindfulness, MindfulnessDTO.class);

        IFileSystem fileSystemInstance = FileSystemFactory.createFileSystemInstance();

        dto.setStatusCn(OnOffStatusEnum.getTypeName(dto.getStatus()));
        dto.setBgPicUrl(fileSystemInstance.getFilePathById(dto.getBgPicId()));
        dto.setVideoUrl(fileSystemInstance.getFilePathById(dto.getVideoId()));
        dto.setCoverPicUrl(fileSystemInstance.getFilePathById(dto.getCoverPicId()));

        dto.setLastdate(mindfulness.getSort());
        dto.setShowTime(DateUtils.parseTimeToDateStr(mindfulness.getCreateTime()));

        dto.setCollectCount(attentionBLL.countResourceAttetion(mindfulness.getId()));
        if (StringUtils.isNotEmpty(operUserId)){
            dto.setHasCollect(attentionBLL.hasAttetion(operUserId, mindfulness.getId()));
        }

        return dto;
    }

    @Override
    public boolean onSale(String operUserId, String mindfulnessId) {
        Mindfulness mindfulness = mindfulnessService.selectById(mindfulnessId);
        if (mindfulness == null){
            throw new ServiceException("正念音频不存在");
        }
        mindfulness.setStatus(OnOffStatusEnum.ON_SALE.getNo());
        return mindfulnessService.updateById(mindfulness);
    }

    @Override
    public boolean offSale(String operUserId, String mindfulnessId) {
        Mindfulness mindfulness = mindfulnessService.selectById(mindfulnessId);
        if (mindfulness == null){
            throw new ServiceException("正念音频不存在");
        }
        mindfulness.setStatus(OnOffStatusEnum.OFF_SALE.getNo());
        return mindfulnessService.updateById(mindfulness);
    }

    @Override
    public List<MindfulnessDTO> list(String operUserId, String keyword, Long lastDate, int pageSize) {
        EntityWrapper<Mindfulness> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.eq("STATUS", OnOffStatusEnum.ON_SALE.getNo());

        if (lastDate != null && lastDate != 0){
            entityWrapper.gt("SORT", lastDate);
        }

        if (StringUtils.isNotEmpty(keyword)){
            entityWrapper.and("(NAME like {0})", '%' + keyword + '%');
        }

        entityWrapper.orderBy("SORT", true);
        Page<Mindfulness> mindfulnessPage = mindfulnessService.selectPage(new Page<Mindfulness>(1, pageSize), entityWrapper);
        List<Mindfulness> records = mindfulnessPage.getRecords();
        if (records == null){
            return null;
        }

        List<MindfulnessDTO> list = new ArrayList<>();
        for (Mindfulness record : records) {
            list.add(trans(operUserId, record));
        }
        return list;
    }

    @Override
    public boolean up(String operUserId, String id) {
        Mindfulness curr = mindfulnessService.selectById(id);
        EntityWrapper<Mindfulness> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.lt("SORT", curr.getSort());
        entityWrapper.orderBy("SORT", false);
        Mindfulness pre = mindfulnessService.selectOne(entityWrapper);
        if (pre == null){
            throw new ServiceException("无法上移");
        }

        long tempSort = curr.getSort();
        curr.setSort(pre.getSort());
        pre.setSort(tempSort);

        List<Mindfulness> list = new ArrayList<>();
        list.add(curr);
        list.add(pre);
        return mindfulnessService.updateBatchById(list);
    }

    @Override
    public boolean down(String operUserId, String id) {
        Mindfulness curr = mindfulnessService.selectById(id);
        EntityWrapper<Mindfulness> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("IS_DELETED", BooleanType.FALSE.getCode());
        entityWrapper.gt("SORT", curr.getSort());
        entityWrapper.orderBy("SORT", true);
        Mindfulness after = mindfulnessService.selectOne(entityWrapper);
        if (after == null){
            throw new ServiceException("无法下移");
        }

        long tempSort = curr.getSort();
        curr.setSort(after.getSort());
        after.setSort(tempSort);

        List<Mindfulness> list = new ArrayList<>();
        list.add(curr);
        list.add(after);
        return mindfulnessService.updateBatchById(list);
    }

    @Override
    public boolean top(String operUserId, String id) {
        Mindfulness mindfulness = mindfulnessService.selectById(id);
        Long minSort = mindfulnessMapper.getMinSort(null);
        if (minSort == null){
            return false;
        }else{
            // 不允许出现0，否则分页排序会有问题
            mindfulness.setSort(minSort == 1 ? minSort - 2 : minSort - 1);
        }
        return mindfulnessService.updateById(mindfulness);
    }

    @Override
    public Mindfulness get(String id) {
        return mindfulnessService.selectById(id);
    }

    @Override
    public String save(String operUserId, Mindfulness bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, Mindfulness bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        Mindfulness mindfulness = mindfulnessService.selectById(ids);
        if (mindfulness == null){
            throw new ServiceException("正念音频不存在");
        }
        mindfulness.setIsDeleted(BooleanType.TRUE.getCode());
        mindfulness.setDeleteOperatorId(operUserId);
        mindfulness.setDeletedTime(System.currentTimeMillis());
        return mindfulnessService.updateById(mindfulness);
    }

    @Override
    public void addCache(Mindfulness bean) {

    }

    @Override
    public void removeCache(Mindfulness bean) {

    }
}
