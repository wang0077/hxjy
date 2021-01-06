package com.lcy.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 1，刚刚 2，一小时前 3，两小时前 4，一天前 5，两天前 6，三天前
 * @author liqingju
 * 
 */
public class TimeUtils {

	private static final long ONEDATE = 86400000; // 一天的时间差

	private static final long ONEMINUTE = 60000; // 一分钟的时间差

	/**
	 * 
	 * 
	 * time类型 是 yyyy-MM-dd HH:mm:ss.s 不是的请转化成这个
	 * 
	 * time 未与当前时间相比的时间 time 就是未来的时间
	 * 
	 * @param time
	 * @return
	 */

	public static String getDateStr(long times) {
		String dateStr = null;

		// SimpleDateFormat dd = new SimpleDateFormat("dd");
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date otheTime = new Date(times);
		Date nowTime = new Date();
		
		Long time11 = nowTime.getTime();
		Long time10 = otheTime.getTime();
	
		long timeDiff = time11 - time10;

		if (timeDiff >= ONEDATE) {
			String someDate = someDate(timeDiff,times);
			return someDate == null ? yyyyMMdd.format(otheTime) : someDate;
		}
		
		if (timeDiff < ONEDATE) {
			return someMinute(timeDiff,times);
		}

		return dateStr;
	}
	
	private static String someMinute(long timeDiff,long times) {
		
		SimpleDateFormat hh = new SimpleDateFormat("HH");
		
		SimpleDateFormat yyyyMMdd = null;
		Date otheTime = new Date(times);
		Date nowTime = new Date();
		
		if(Integer.valueOf(hh.format(nowTime))>=Integer.valueOf(hh.format(otheTime))){
			yyyyMMdd = new SimpleDateFormat("HH:mm");
		}else{
			yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		
		return yyyyMMdd.format(times);
		

	}

	/**
	 * 社交
	 * @param times
	 * @return
	 */
	public static String getDateStrForSocial(long times) {
		String dateStr = null;

		// SimpleDateFormat dd = new SimpleDateFormat("dd");
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date otheTime = new Date(times);
		Date nowTime = new Date();
		
		Long time11 = nowTime.getTime();
		Long time10 = otheTime.getTime();
	
		long timeDiff = time11 - time10;

		if (timeDiff >= ONEDATE) {
			String someDate = someDate(timeDiff,times);
			return someDate == null ? yyyyMMdd.format(otheTime) : someDate;
		}
		
		if (timeDiff < ONEDATE) {
			return someMinuteForSacial(timeDiff);
		}

		return dateStr;
	}

	private static String someMinuteForSacial(long timeDiff) {
		int dateNum = (int) (timeDiff / ONEMINUTE);
	
		String dateStr = null;
		if (dateNum < 5) {
			dateStr = "刚刚";
		}else  if (dateNum >= 5 && dateNum < 60)
			dateStr = dateNum + "分钟前";
		else if (dateNum >= 60 && dateNum < 60*24 ) {
			int hour = dateNum/60;
			dateStr = hour + "小时前";
		} 

		return dateStr;

	}

	/**
	 * 获取剩余时间
	 * 
	 * @param timeMillis 毫秒
	 * @return
	 */
	public static String getRemainTime(long timeMillis) {

		int dateNum = (int) (timeMillis / 1000);

		String dateStr = null;
		if (dateNum < 60) {
			dateStr = dateNum + "秒";
		} else if (dateNum < 60 * 60) {
			int minute = dateNum / 60;
			dateStr = minute + "分钟";
		} else {
			int hour = dateNum / 60 / 60;
			dateStr = hour + "小时";
		}

		return dateStr;

	}

	/**
	 * 
	 * 用于判断相差月数
	 * 
	 * @param timeDiff
	 * @return
	 */
	private static String someDate(long timeDiff,long times) {
		
		int dateNum = (int) (timeDiff / ONEDATE);
		
		String dateStr = null;
		
		if (dateNum < 365 )  {
			SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateStr = yyyyMMdd.format(new Date(times));
		}else{
			SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = yyyyMMdd.format(new Date(times));
		}
		
		return dateStr;
	}


	/**
	 * 
	 * 数字转化成中国的一二三
	 * 
	 * 
	 * @param num
	 * @return
	 */
	public static String enNum2CnNum(String num) {
		String[] CnNum = new String[] { "零", "一", "二", "三", "四", "五", "六", "七",
				"八", "九" };
		// 自己最大值 只需要 在这里添加就行了 其他代码不用改 例如到 亿
		String[] Cn10Num = new String[] { "十", "百", "千", "万" };
		String CnStr = "";

		try {
			char[] strArray = num.toCharArray();
			int length = strArray.length;
			for (int i = 0; i < strArray.length; i++) {
				if (length > 1 && ((char2Int(strArray[i])) != 0)) {
					CnStr += (CnNum[char2Int(strArray[i])] + Cn10Num[length - 2]);
				} else {
					CnStr += CnNum[char2Int(strArray[i])];
				}
				length--;
			}
			// 处理多个零字段
			CnStr = CnStr.replaceAll("零零*", "零");
			// 处理最后一个是零
			if (CnStr.lastIndexOf("零") + 1 == CnStr.length()) {
				CnStr = CnStr.replaceAll("零$", "");
			}
			// 处理十几
			if (CnStr.contains("十")
					&& (CnStr.length() == 3 || CnStr.length() == 2)) {
				CnStr = CnStr.replaceAll("^一", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CnStr;

	}

	/**
	 * 获取单个的String
	 * 
	 * @param cha
	 * @return
	 */
	private static Integer char2Int(char cha) {
		return Integer.valueOf(cha + "");
	}

	public static void main(String[] args) {
		System.out.println(getDateStr(1506613803000L));
		// System.out.println(enNum2CnNum("10000"));

	}
}