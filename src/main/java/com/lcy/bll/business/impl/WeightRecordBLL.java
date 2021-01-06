package com.lcy.bll.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lcy.autogenerator.entity.WeightRecord;
import com.lcy.autogenerator.mapper.WeightRecordMapper;
import com.lcy.autogenerator.service.IWeightRecordService;
import com.lcy.bll.business.IClockInRecordBLL;
import com.lcy.bll.business.IWeightRecordBLL;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.business.WeightRecordDTO;
import com.lcy.dto.business.WeightRecordListDTO;
import com.lcy.util.business.AbstractBO;
import com.lcy.util.common.DateUtils;
import com.lcy.util.common.ModelMapperUtils;
import com.lcy.util.common.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeightRecordBLL extends AbstractBO<WeightRecord> implements IWeightRecordBLL {

    @Autowired
    IWeightRecordService weightRecordService;

    @Autowired
    WeightRecordMapper weightRecordMapper;

    @Autowired
    IClockInRecordBLL clockInRecordBLL;

    @Override
    public WeightRecord get(String id) {
        return null;
    }

    @Override
    public String save(String operUserId, WeightRecord bean) {
        return null;
    }

    @Override
    public boolean update(String operUserId, WeightRecord bean) {
        return false;
    }

    @Override
    public boolean delete(String operUserId, String ids) {
        return false;
    }

    @Override
    public void addCache(WeightRecord bean) {

    }

    @Override
    public void removeCache(WeightRecord bean) {

    }

    @Override
    @Transactional
    public boolean save(String userId, String weight) {

        // 校验能不能记录
        WeightRecordDTO weightRecordDTO = getWeightRecordDTO(userId);
        if (!weightRecordDTO.isCanSet()){
            throw new ServiceException("您这周已完成体重检测");
        }

        WeightRecord weightRecord = new WeightRecord();
        weightRecord.setId(UUIDGenerator.getUUID());
        weightRecord.setUserId(userId);
        weightRecord.setWeight(weight);
        weightRecord.setCreateTime(System.currentTimeMillis());
        weightRecord.setDate(DateUtils.parseTimeToDate(weightRecord.getCreateTime()));
        return weightRecordService.insert(weightRecord) && clockInRecordBLL.weightRecordClockIn(userId, weight);
    }

    @Override
    public WeightRecordDTO getWeightRecordDTO(String userId) {
        WeightRecordDTO weightRecordDTO = new WeightRecordDTO();

        List<WeightRecordListDTO> dtoList = new ArrayList<>();
        boolean canSet = false;
        String lastTimeStr = "";

        long currentTimeMillis = System.currentTimeMillis();

        List<WeightRecord> list = list(userId);
        if (list == null || list.isEmpty()){
            canSet = true;
        } else {
            WeightRecord weightRecord;
            WeightRecordListDTO listDTO = null;
            long lastTimeMills = 0;
            long thisTimeMills;
            long sevenDayTime = 7 * 24 * 3600 * 1000;
            for (int i = 0; i < list.size(); i++) {
                weightRecord = list.get(i);

                // 当前记录的时间
                thisTimeMills = DateUtils.parseDateStrToTime(weightRecord.getDate() + " 00:00:00");

                // 非第一条需要比较中间是否几周没去记录体重
                if (i != 0){
                    while (thisTimeMills - lastTimeMills > sevenDayTime){
                        listDTO = new WeightRecordListDTO();
                        dtoList.add(listDTO);
                        lastTimeMills = lastTimeMills + sevenDayTime;
                    }
                }

                listDTO = ModelMapperUtils.map(weightRecord, WeightRecordListDTO.class);
                listDTO.setCreateTimeStr(DateUtils.parseTimeToDateStr(listDTO.getCreateTime()));
                dtoList.add(listDTO);

                // 最后一条保存的时间
                lastTimeMills = DateUtils.parseDateStrToTime(listDTO.getDate() + " 00:00:00");

                if (i == list.size() - 1){
                    lastTimeStr = weightRecord.getDate();
                    canSet = currentTimeMillis - thisTimeMills > sevenDayTime;
                }
            }
        }

        weightRecordDTO.setCanSet(canSet);
        weightRecordDTO.setLastTimeStr(lastTimeStr);
        weightRecordDTO.setWeightRecordList(dtoList);
        return weightRecordDTO;
    }

    @Override
    public List<WeightRecord> list(String userId) {
        EntityWrapper<WeightRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("USER_ID", userId);
        entityWrapper.orderBy("DATE", true);
        return weightRecordService.selectList(entityWrapper);
    }
}
