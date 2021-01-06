package com.lcy.util.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具，用于异步线程操作
 * 
 * @author cguangcong@linewell.com
 * @since 2016-03-11
 *
 */
public class ThreadPoolUtils {
	
	/**
	 * 线程池对象
	 */
	private static ExecutorService threadPool = null;

	/**
	 * 获取线程池对象
	 * 
	 * @return
	 */
	public static ExecutorService getInstance(){
		if(null==threadPool){
			threadPool = Executors.newCachedThreadPool();
		}
		return threadPool;
	}
}
