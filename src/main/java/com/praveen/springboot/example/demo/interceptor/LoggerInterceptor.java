package com.praveen.springboot.example.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoggerInterceptor  extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Calculating the time to complete request");
        long startTime = System.currentTimeMillis();
        logger.info("Request url is {} started on {}", request.getRequestURL(), startTime);
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Finished Calculating the time to complete request");
        long endTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("startTime");
        logger.info("Request url {} finished on {}", request.getRequestURL(), endTime);
        logger.info("Total time taken {} milli Seconds", endTime - startTime);
    }
}
