package com.lcy.util.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 序列号工具类
 * 
 * @author chenxiaowei@linewell.com
 * @since 2017-07-18
 */
public class SerialNumUtils {

	// 序号每个端的位数
	private static final int SERIAL_NUM = 4;
	
	/**
	 * 进行序号的累加
	 * @param sequence  原始序号，如果为空，则从1开始
	 * @return 累加后的序号
	 */
	private static String addSerialNum(String serialNum){
		String seq = "";
		if(StringUtils.isEmpty(serialNum)){
			seq = StringUtils.leftPad("1", SERIAL_NUM, "0");
		} else {
			String cur = StringUtils.right(serialNum, SERIAL_NUM);
			
			//累加1
			int count = Integer.valueOf(cur) + 1;
			
			String seqpad = StringUtils.leftPad(count +"", SERIAL_NUM, "0");
			
			seq = StringUtils.substring(serialNum, 0, serialNum.length() - SERIAL_NUM);
			
			seq += seqpad; 
		}
		return seq;
	}
	
	/**
	 * 获取下个序号
	 * @param levelMaxSerialNum 层级当中的最大序号
	 * @param pSerialNum        父的序号
	 * @return Sequence
	 */
	public static String getNextSerialNum(String levelMaxSerialNum, String pSerialNum){
		
		String nextSerialNum = "";
		
		// 最大的序号不为空，则直接累加
		if(StringUtils.isNotEmpty(levelMaxSerialNum)){
			nextSerialNum = levelMaxSerialNum;
			nextSerialNum = addSerialNum(nextSerialNum);
		} else {// 没有存在最大的序号，序号的累加由上级序号与本级初始的序号的累加
			nextSerialNum = addSerialNum(nextSerialNum);
			nextSerialNum = pSerialNum + nextSerialNum;
		}
		return nextSerialNum;
	}
	
	/**
	 * 把序列号进行分隔 
	 * @param serialNum "1001200130014001"
	 * @return [1001, 10012001, 100120013001, 1001200130014001]
	 */
	public static List<String> getParentSerialNums(String serialNum){
		List<String> numList = new ArrayList<String>();
		
		// 如果序列号为空或者已经到顶级的序列号，则返回为空
		if(StringUtils.isEmpty(serialNum)){
			return null;
		}
		
		int serialNumLen = serialNum.length();
		// 不是合法的序列号或者只有一级，直接返回
		if(serialNumLen % SERIAL_NUM != 0 || serialNumLen == SERIAL_NUM){
			numList.add(serialNum);
			return numList;
		}
		
		int count = serialNumLen / SERIAL_NUM;
		
		for(int i = 1; i <= count ; i++){
			numList.add(StringUtils.substring(serialNum, 0,	i * SERIAL_NUM));
		}
		
		return numList;
	}
	
}
