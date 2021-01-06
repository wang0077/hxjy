package com.lcy.util.excel;

import com.lcy.autogenerator.entity.UserDailyStatistics;
import com.lcy.dto.user.LabelDTO;
import com.lcy.dto.user.UserBaseDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportUtils {

    /**
     * 输出excel 字符流
     * @param response
     * @param userBaseDTOList
     */
    public static void exportExcelUser(HttpServletResponse response, List<UserBaseDTO> userBaseDTOList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "会员列表";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 9;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            headers[0] = "序号";
            headers[1] = "用户昵称";
            headers[2] = "真实姓名";
            headers[3] = "联系方式";
            headers[4] = "性别";
            headers[5] = "标签";
            headers[6] = "消费次数";
            headers[7] = "消费金额";
            headers[8] = "参与测评数";

            columns[0] = "0";
            columns[1] = "1";
            columns[2] = "2";
            columns[3] = "3";
            columns[4] = "4";
            columns[5] = "5";
            columns[6] = "6";
            columns[7] = "7";
            columns[8] = "8";

            if (userBaseDTOList != null) {
                int i = 0;
                for (UserBaseDTO baseDTO : userBaseDTOList) {
                    map = new LinkedHashMap<String, Object>();
                    map.put(columns[0], ++i);
                    map.put(columns[1], baseDTO.getNickname());
                    map.put(columns[2], baseDTO.getName());
                    map.put(columns[3], baseDTO.getPhone());
                    map.put(columns[4], baseDTO.getGender() == 1 ? "男" : baseDTO.getGender() == 2 ? "女" : "");
                    List<LabelDTO> labelList = baseDTO.getLabelList();
                    StringBuilder sb = new StringBuilder();
                    if(labelList != null){
                        for (LabelDTO labelDTO : labelList) {
                            sb.append(labelDTO.getName()).append(" ");
                        }
                    }
                    map.put(columns[5], sb.toString());
                    map.put(columns[6], baseDTO.getBuyCount());
                    map.put(columns[7], baseDTO.getConsumeMoneyStr());
                    map.put(columns[8], baseDTO.getAnswerCount());
                    result.add(map);
                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { firstTitle.trim() };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param userBaseDTOList
     */
    public static void exportExcelUserBase(HttpServletResponse response, List<UserBaseDTO> userBaseDTOList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "基本信息";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 9;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            headers[0] = "ID";
            headers[1] = "性别";
            headers[2] = "年龄";
            headers[3] = "婚姻状况";
            headers[4] = "教育程度";
            headers[5] = "职业";
            headers[6] = "身高（cm）";
            headers[7] = "体重（kg）";
            headers[8] = "BMI";

            columns[0] = "0";
            columns[1] = "1";
            columns[2] = "2";
            columns[3] = "3";
            columns[4] = "4";
            columns[5] = "5";
            columns[6] = "6";
            columns[7] = "7";
            columns[8] = "8";

            if (userBaseDTOList != null) {
                int i = 0;
                for (UserBaseDTO baseDTO : userBaseDTOList) {
                    map = new LinkedHashMap<String, Object>();
                    map.put(columns[0], baseDTO.getEdId());
                    map.put(columns[1], baseDTO.getGender());

                    map.put(columns[2], baseDTO.getAge());

                    map.put(columns[3], baseDTO.getMarital());
                    map.put(columns[4], baseDTO.getEducation());
                    map.put(columns[5], baseDTO.getJob());
                    map.put(columns[6], baseDTO.getHeight());
                    map.put(columns[7], baseDTO.getWeight());
                    map.put(columns[8], baseDTO.getBmi());
                    result.add(map);
                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { firstTitle.trim() };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelMindfulness(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                              boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "正念技能";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {

//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "正念技能练习次数" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            result = new ArrayList<>();

            if (allUserDayIndexData != null) {

                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount2());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                } else {

                    for (String userId : userIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], userId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount2());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                }
            }

            title = new String[] { "正念技能练习时长（min）" };
            workbook = ExcelUtils.exportExcelAppend(workbook, title, headers,columns, result);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelMindfulnessCount(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                                   boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "正念技能练习次数";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {

//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "正念技能练习次数" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelMindfulnessTime(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                                  boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "正念技能练习时长（min）";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {

//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount2());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount2());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "正念技能练习时长（min）" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelRegularDiet(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                              boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "食物规则技能";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {
//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "设立食物规则天数" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelDietDiary(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                            boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "饮食日记";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {

//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "暴食次数" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            result = new ArrayList<>();

            if (allUserDayIndexData != null) {


                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount2());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                } else {

                    for (String userId : userIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], userId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount2());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                }
            }

            title = new String[] { "暴食冲动次数" };
            workbook = ExcelUtils.exportExcelAppend(workbook, title, headers,columns, result);

            result = new ArrayList<>();

            if (allUserDayIndexData != null) {


                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getValue());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                } else {

                    for (String userId : userIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], userId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getValue());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                }
            }

            title = new String[] { "完成百分比(完成栏/总栏数)" };
            workbook = ExcelUtils.exportExcelAppend(workbook, title, headers,columns, result);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelDietDiaryGluttonyTimes(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                                         boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "饮食日记-暴食次数";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {

//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "饮食日记-暴食次数" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelDietDiaryGluttonyImpulseTimes(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                                                boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "饮食日记-暴食冲动次数";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {


//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount2());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount2());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "饮食日记-暴食冲动次数" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelDietDiaryPercent(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                                   boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "饮食日记-完成百分比";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {


//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getValue());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getValue());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "饮食日记-完成百分比" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelLuckyBox(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData2, int day,
                                           boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "lucky box";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {


//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "愉快事件完成数量" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            result = new ArrayList<>();

            if (allUserDayIndexData2 != null) {


                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                } else {

                    for (String userId : userIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], userId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                }
            }

            title = new String[] { "痛苦事件完成数量" };
            workbook = ExcelUtils.exportExcelAppend(workbook, title, headers,columns, result);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelHappyEvent(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                             boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "愉快事件完成数量";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {


//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "愉快事件完成数量" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelPainEvent(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                            boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "痛苦事件完成数量";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + day;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "天";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {


//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getCount());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getCount());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "痛苦事件完成数量" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    /**
     * 输出excel 字符流
     * @param response
     * @param allUserDayIndexData
     */
    public static void exportExcelWeightRecord(HttpServletResponse response, LinkedHashMap<String, LinkedHashMap<Integer, UserDailyStatistics>> allUserDayIndexData, int day,
                                            boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList){

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = "体重监测";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = day / 7 + 1;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else{
                    headers[j] = "第" + j + "周";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {


//                if (laboratoryPerson){
                    for (String edId : userEdIdList) {
                        map = new LinkedHashMap<String, Object>();
                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(edId);

                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getValue());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
//                } else {
//
//                    for (String userId : userIdList) {
//                        map = new LinkedHashMap<String, Object>();
//                        LinkedHashMap<Integer, UserDailyStatistics> value = allUserDayIndexData.get(userId);
//
//                        for (int j = 0; j < sum; j++){
//                            if (j == 0){
//                                map.put(columns[j], userId);
//                            }else{
//                                if (value != null && value.containsKey(j) && value.get(j) != null){
//                                    map.put(columns[j], value.get(j).getValue());
//                                } else {
//                                    map.put(columns[j], 0);
//                                }
//                            }
//                        }
//                        result.add(map);
//                    }
//                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { "体重监测" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }

    public static void exportExcelScale(HttpServletResponse response, LinkedHashMap<String,List<LinkedHashMap<Integer,UserDailyStatistics>>> allUserDayIndexData, boolean isBase, boolean laboratoryPerson, List<String> userIdList, List<String> userEdIdList) {

        // 获取输出流
        OutputStream out = null;

        // 设置作为Excel标题的字符串
        String firstTitle = isBase ? "基线量表" : "后测量表";

        try {

            // 设置内容类型
            response.setContentType("octets/stream");

            // 设置响应头
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String((firstTitle).getBytes("GB2312"), "iso8859-1")+ ".xls");

            // 获取输出流
            out = response.getOutputStream();

            // 存放数据对象的容器
            List<LinkedHashMap<String, Object>> result = new ArrayList<LinkedHashMap<String, Object>>();

            // 存放数据的对象
            LinkedHashMap<String, Object> map = null;

            // 记录列表的字段个数
            int sum = 1 + 5;

            // 存放字段中文名作为Excel表头
            String[] headers = new String[sum];

            // 存放字段英文名作为列名
            String[] columns = new String[sum];

            for (int j = 0; j < sum; j++){
                if (j == 0){
                    headers[j] = "ID";
                }else if (j == 1){
                    headers[j] = "SCOFF";
                }else if (j == 2){
                    headers[j] = "EAT-26";
                }else if (j == 3){
                    headers[j] = "PHQ-9";
                }else if (j == 4){
                    headers[j] = "GAD-7";
                }else if (j == 5){
                    headers[j] = "SES";
                }
                columns[j] = j + "";
            }

            if (allUserDayIndexData != null) {

                for (String edId : userEdIdList) {
                    map = new LinkedHashMap<String, Object>();

                    if (!allUserDayIndexData.containsKey(edId)) {
                        continue;
                    }
                    List<LinkedHashMap<Integer, UserDailyStatistics>> valueList = allUserDayIndexData.get(edId);

                    for (LinkedHashMap<Integer, UserDailyStatistics> value : valueList) {
                        for (int j = 0; j < sum; j++){
                            if (j == 0){
                                map.put(columns[j], edId);
                            }else{
                                if (value != null && value.containsKey(j) && value.get(j) != null){
                                    map.put(columns[j], value.get(j).getValue());
                                } else {
                                    map.put(columns[j], 0);
                                }
                            }
                        }
                        result.add(map);
                    }
                }
            }

            // 标题数组（根据需求，可存放多级标题）
            String[] title = new String[] { isBase ? "基线量表" : "后测量表" };

            // 调用导出到Excel的工具类
            HSSFWorkbook workbook = ExcelUtils.exportExcel(title, headers,columns, result, null);

            // 输出Excel表格
            workbook.write(out);

        } catch (IOException e) {

        }

    }
}
