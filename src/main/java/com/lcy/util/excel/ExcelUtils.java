package com.lcy.util.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


/**
 * Excel工具类
 * 
 * @author zjingcan@linewell.com
 * @2015-12-16
 */
public class ExcelUtils {
	
	/**
	 * 从Excel中导入数据（Bean对象使用ExcelFieldType进行标记，标记每个属性的列的索引，从第二行开始导入）
	 * @param path
	 * @param clz
	 * @return
	 * @throws Exception 
	 */
	public static <T> List<T> importExcel(String path, Class<T> clz) throws Exception{
		InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        T xlsDto = null;
        List<T> list = new ArrayList<T>();
        
        // 开始构建列对应的数据
        Field[] fields = clz.getDeclaredFields();
        Map<String, Integer> fieldsIndexMap = new HashMap<String, Integer>();
        String fieldName;
        int columnIndex;
        for(Field field : fields){
        	if(field.isAnnotationPresent(ExcelFiledType.class)){
        		fieldName = field.getName();
        		columnIndex = field.getAnnotation(ExcelFiledType.class).columnIndex();
        		fieldsIndexMap.put(fieldName, columnIndex);
        	}
        }
        
        HSSFCell cell = null;
        String setMethodName = null;
        Method setMethod = null;
        Field field = null;
        String cellValue = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
          
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                
                xlsDto = clz.newInstance();
                
                for(Entry<String, Integer> item : fieldsIndexMap.entrySet()){
                	fieldName = item.getKey();
                	columnIndex = item.getValue();
                	cell = hssfRow.getCell(columnIndex);
                	setMethodName = "set"  
                            + fieldName.substring(0, 1).toUpperCase()  
                            + fieldName.substring(1);
                	field = clz.getDeclaredField(fieldName);
                	setMethod = clz.getMethod(setMethodName, field.getType());
                	
                	if (cell == null || HSSFCell.CELL_TYPE_ERROR == cell.getCellType()) {
                		cellValue = null;
                	} else if (HSSFCell.CELL_TYPE_BLANK == cell.getCellType()) {
                		cellValue = "";
                	} else if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
                		cellValue = String.valueOf(cell.getBooleanCellValue());
                	} else if (HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
                		cellValue = cell.getCellFormula();
                	} else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                		cellValue = new BigDecimal(cell.getNumericCellValue()).toString();
                	} else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                		cellValue = StringUtils.trim(cell.getStringCellValue());
                	}
                	
                	if(field.getType() == String.class){
                		setMethod.invoke(xlsDto, cellValue);
                	}else if(field.getType() == Float.class || field.getType() == float.class){
                		setMethod.invoke(xlsDto, Float.valueOf(cell.getStringCellValue()));
                	}else if(field.getType() == int.class || field.getType() == Integer.class){
                		setMethod.invoke(xlsDto, Integer.valueOf(cell.getStringCellValue()));
                	}else if(field.getType() == Date.class){
                		setMethod.invoke(xlsDto, sdf.parse(cell.getStringCellValue()));
                	}else if(field.getType() == Long.class || field.getType() == long.class){
                		setMethod.invoke(xlsDto, Long.valueOf(cell.getStringCellValue()));
                	}
                	
                }// end for
                
                list.add(xlsDto);
            }// end 循环行Row
        }// end 循环工作表Sheet
//        hssfWorkbook.close();
        
        return list;
	}

	/** 
     * 导出Excel的方法 
     * @param title [] 标题数组  
     * @param headers [] 表头中文名数组
     * @param columns [] 表头列名数组
     * @param result 结果集 
     * @param listMaps 字典键值对容器
	 * @throws IOException 异常信息
     */  
	public static HSSFWorkbook exportExcel(String[] title, String[] headers,
                                           String[] columns, List<LinkedHashMap<String, Object>> result, List<LinkedHashMap<String, Object>> listMaps) throws IOException {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格并设置表头
		HSSFSheet sheet = workbook.createSheet(title[0]);
		// 设置默认行索引
		int rowDefaultIndex = 0;
		// 标题个数
		int len = title.length;
		// 列个数
		int count = headers.length;
		// 设置默认列宽
		sheet.setDefaultColumnWidth(20);
		
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, count - 1));
		
		// 多个标题合并多行单元格
		if (len > 0) {
			for (int i = 1; i < len; i++) {
				sheet.addMergedRegion(new CellRangeAddress(i, i, 0, count - 1));
			}
		}

		/*设置标题样式 start*/
		HSSFCellStyle styleTitle = workbook.createCellStyle();
		styleTitle = setCommonStyle(styleTitle);
		// 左右居中
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置标题字体
		HSSFFont fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short) 12);
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把表头字体应用到表头样式
		styleTitle.setFont(fontTitle);
		/*设置标题样式 end*/
		
		/*设置表头样式 start*/ 
		HSSFCellStyle styleHeader = workbook.createCellStyle();
		styleHeader = setCommonStyle(styleHeader);
		// 左右居中
		styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		styleHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/* 设置表头样式  end */
		
		/*设置表格数据样式 start*/
		HSSFCellStyle styleColumn = workbook.createCellStyle();
		styleColumn = setCommonStyle(styleColumn);
		/*设置表格数据样式 end*/

		// 创建标题行
		HSSFRow row = sheet.createRow(rowDefaultIndex++);
		HSSFCell cell = null;
		row.setHeight((short) 1000);
		
		// 遍历设置边框和标题
		for (int i = 0; i < count; i++) {
		    cell = row.createCell(i);
			cell.setCellStyle(styleTitle);
			if (i == 0) {
				cell.setCellValue(title[0]);
			}
		}
		
		// 包含多级标题
		if (len > 0 ) {
			for (int i = 1; i < len; i++) {
				row = sheet.createRow(rowDefaultIndex++);
				
				// 遍历设置边框和标题
				for (int j = 0; j < count; j++) {
				    cell = row.createCell(j);
					cell.setCellStyle(styleColumn);
					if (j == 0) {
						cell.setCellValue(title[i]);
					}
				}
			}
		}
		
		// 创建表头行
		row = sheet.createRow(rowDefaultIndex++);
		for (int i = 0; i < count; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(styleHeader);
			cell.setCellValue(headers[i]);
		}

		// 遍历集合数据，产生数据行
		if (result != null) {

			int rowIndex = rowDefaultIndex;

			// 遍历提取值
			for (LinkedHashMap<String, Object> map : result) {

				int cellIndex = 0;

				// 创建行
				row = sheet.createRow(rowIndex);

				// 遍历给单元格赋值
				for (String str : columns) {

					HSSFCell cellcolumn = row.createCell(cellIndex);
					// 设置样式
					cellcolumn.setCellStyle(styleColumn);

					HSSFRichTextString columnString = new HSSFRichTextString(
							map.get(str) == null ? "" : map.get(str).toString());

					// 给单元格设值
					cellcolumn.setCellValue(columnString);

					cellIndex++;
				}
				rowIndex++;
			}
		}else {// 没有结果集对象则为导出模版操作
			
			for (int i = 0; i < columns.length; i++) {
				
				// 行索引
				int rowIndex = rowDefaultIndex;
				
				for (int j = 0; j < listMaps.size(); j++) {
					
					LinkedHashMap<String, Object> listMap = listMaps.get(j);
					
					if (listMap.get(columns[i]) != null) {
						
						String[] list = (String[]) listMap.get(columns[i]);
						//生成下拉框内容  
					    DVConstraint constraint = DVConstraint.createExplicitListConstraint(list);
					    CellRangeAddressList regions = null;
					    HSSFDataValidation data_validation = null;
					    
						regions = new CellRangeAddressList(rowIndex,
								rowIndex + 30, i, i);
						data_validation = new HSSFDataValidation(regions,
								constraint);
						// 对sheet页生效
						sheet.addValidationData(data_validation);
					}
				}
				rowIndex++;
			}
		}
		// workbook.write(out);
		return workbook;
	}

	public static HSSFWorkbook exportExcelAppend(HSSFWorkbook workbook, String[] title, String[] headers,
										   String[] columns, List<LinkedHashMap<String, Object>> result) throws IOException {

		// 生成一个表格并设置表头
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 设置默认行索引
		int rowDefaultIndex = sheet.getLastRowNum() + 2;
		// 标题个数
		int len = title.length;
		// 列个数
		int count = headers.length;
		// 设置默认列宽
		sheet.setDefaultColumnWidth(20);

		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, count - 1));

		// 多个标题合并多行单元格
		if (len > 0) {
			for (int i = 1; i < len; i++) {
				sheet.addMergedRegion(new CellRangeAddress(i, i, 0, count - 1));
			}
		}

		/*设置标题样式 start*/
		HSSFCellStyle styleTitle = workbook.createCellStyle();
		styleTitle = setCommonStyle(styleTitle);
		// 左右居中
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置标题字体
		HSSFFont fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short) 12);
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把表头字体应用到表头样式
		styleTitle.setFont(fontTitle);
		/*设置标题样式 end*/

		/*设置表头样式 start*/
		HSSFCellStyle styleHeader = workbook.createCellStyle();
		styleHeader = setCommonStyle(styleHeader);
		// 左右居中
		styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		styleHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/* 设置表头样式  end */

		/*设置表格数据样式 start*/
		HSSFCellStyle styleColumn = workbook.createCellStyle();
		styleColumn = setCommonStyle(styleColumn);
		/*设置表格数据样式 end*/

		// 创建标题行
		HSSFRow row = sheet.createRow(rowDefaultIndex++);
		HSSFCell cell = null;
		row.setHeight((short) 1000);

		// 遍历设置边框和标题
		for (int i = 0; i < count; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(styleTitle);
			if (i == 0) {
				cell.setCellValue(title[0]);
			}
		}

		// 包含多级标题
		if (len > 0 ) {
			for (int i = 1; i < len; i++) {
				row = sheet.createRow(rowDefaultIndex++);

				// 遍历设置边框和标题
				for (int j = 0; j < count; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(styleColumn);
					if (j == 0) {
						cell.setCellValue(title[i]);
					}
				}
			}
		}

		// 创建表头行
		row = sheet.createRow(rowDefaultIndex++);
		for (int i = 0; i < count; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(styleHeader);
			cell.setCellValue(headers[i]);
		}

		// 遍历集合数据，产生数据行
		if (result != null) {

			int rowIndex = rowDefaultIndex;

			// 遍历提取值
			for (LinkedHashMap<String, Object> map : result) {

				int cellIndex = 0;

				// 创建行
				row = sheet.createRow(rowIndex);

				// 遍历给单元格赋值
				for (String str : columns) {

					HSSFCell cellcolumn = row.createCell(cellIndex);
					// 设置样式
					cellcolumn.setCellStyle(styleColumn);

					HSSFRichTextString columnString = new HSSFRichTextString(
							map.get(str) == null ? "" : map.get(str).toString());

					// 给单元格设值
					cellcolumn.setCellValue(columnString);

					cellIndex++;
				}
				rowIndex++;
			}
		}
		// workbook.write(out);
		return workbook;
	}
	
	/**
	 * 设置通用表格样式
	 * @param cellStyle   样式对象
	 * @return  cellStyle 通用样式对象
	 */
	public static HSSFCellStyle setCommonStyle(HSSFCellStyle cellStyle){
		
		// 设置边框（依次：下、左、右、上）线条
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		// 上下居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		// 自动换行
		cellStyle.setWrapText(true);
		
		return cellStyle;
	}
}
