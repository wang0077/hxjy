package com.lcy.springboot.filter;

import com.lcy.util.common.DateUtils;
import com.lcy.util.common.IpUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

//@WebFilter(filterName = "innoCoreFilter", urlPatterns = "/*")
public class InnoCoreFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String ip = null;
        String url = null;
        long beginTime = System.currentTimeMillis();
        HttpServletRequest requestWrapper = null;

        if (request instanceof HttpServletRequest) {
            requestWrapper = new BehaviorHttpServletRequestWrapper((HttpServletRequest) request);
            url = requestWrapper.getRequestURL().toString();
            ip = IpUtil.getLocalIP(requestWrapper);
        }


        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }

        System.out.println("【" + DateUtils.parseTimeToDefaultStr(beginTime) + "】【" + ip + "】" + url + " 时间：" + (System.currentTimeMillis() - beginTime));

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
//		System.out.println("过滤器初始化");		
//		KafkaConsumerUtils.newInstance().consume(
//				new String[]{CoreQueueContants.INTERFACE_ACCESS_QUEUE_NAME}, 
//				new MessageHandler[]{ new InterfaceAccessMessageHandler()});
//		System.out.println("初始化队列完毕");
    }

}
