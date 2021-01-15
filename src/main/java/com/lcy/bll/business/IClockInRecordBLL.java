package com.lcy.bll.business;

import com.lcy.autogenerator.entity.ClockInRecord;
import com.lcy.dto.business.ClockInRecordDTO;
import com.lcy.dto.business.MindfulnessClockInDTO;
import com.lcy.dto.business.RegularDietDTO;
import com.lcy.dto.business.TodayTaskDTO;
import com.lcy.type.business.ClockInRecordTypeEnum;
import com.lcy.util.business.ICommonBO;

import java.util.List;


public interface IClockInRecordBLL extends ICommonBO<ClockInRecord> {

    boolean mindFulnessClockIn(String userId, String resourceId);

//    新添加部分 --------------------------------添加催吐数据---------------------------------------------
    boolean dietDiaryClockIn(String userId, String date, boolean canClockIn, long gluttonyTimes, long gluttonyImpulseTimes,long emeticTimes, String percentValue);
//-------------------------------------------------------------------------------------------------

    MindfulnessClockInDTO getMindFulnessClockIn(String userId);

    List<ClockInRecordDTO> list(String userId, Long lastDate, int pageSize);

    List<ClockInRecord> list(String userId, Long startTime, Long endTime);

    boolean regularDietClockIn(String userId, boolean hasSet, String extraInfo);

    RegularDietDTO getRegularDietDTO(String userId);

    boolean happyEventClockIn(String userId, String resourceId);

    boolean painEventClockIn(String userId, String resourceId);

    boolean weightRecordClockIn(String userId, String weight);

    List<TodayTaskDTO> listTodayTask(String userId);

    int countTodayTodoTask(String userId);

    ClockInRecord get(String userId, ClockInRecordTypeEnum type, String date);
}
