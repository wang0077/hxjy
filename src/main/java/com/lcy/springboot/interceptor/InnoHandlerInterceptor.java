package com.lcy.springboot.interceptor;

import com.lcy.controller.common.ServiceException;
import com.lcy.springboot.filter.BehaviorHttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InnoHandlerInterceptor implements HandlerInterceptor {

	private long startTime ;

	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
	
		startTime = System.currentTimeMillis();
		
		// 请求数据验证异常处理 2017-11-02 add by chenxiaowei
		BehaviorHttpServletRequestWrapper requestWrapper = null;
		if (request instanceof BehaviorHttpServletRequestWrapper) {
			requestWrapper = (BehaviorHttpServletRequestWrapper)request;
			
			if (requestWrapper.isError()) {
				throw new ServiceException(requestWrapper.getErrorMessage(), requestWrapper.getErrorCode());
			}
		}
		
		//System.out.println("执行前:" + readStringFromRequest(request, "UTF-8"));
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

//		InterfaceAccessInfoDTO dto = new InterfaceAccessInfoDTO();
//		dto.setRequestURL(request.getRequestURL().toString());
//		dto.setRequestURI(request.getRequestURI());
//		dto.setParamsJsonStr(readStringFromRequest(request, "UTF-8"));
//		dto.setUseTime(System.currentTimeMillis() - startTime);
//
//		KafkaProducerUtils.newInstance().send(dto, CoreQueueContants.INTERFACE_ACCESS_QUEUE_NAME);

	}

	public static String readStringFromRequest(HttpServletRequest request,
			String encode) {

		StringBuffer json = new StringBuffer();
		InputStream in = null;
		try {
			in = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, encode);
			BufferedReader bufferReader = new BufferedReader(isr);

			String line = bufferReader.readLine();
			if (StringUtils.isEmpty(line))
				line = "";
			do {
				json.append(line);
				line = bufferReader.readLine();
				if (line != null)
					json.append("\n");
			} while (line != null);

			bufferReader.close();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
		}

		return json.toString();
	}

}
