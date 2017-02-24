package com.xuan.xdesktop.restful.www.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuan on 15/3/22.
 * 跨域拦截器
 */
public class AppAccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Headers", "Accept-Ranges");
        response.setHeader("Access-Control-Allow-Headers", "Content-Encoding");
        response.setHeader("Access-Control-Allow-Headers", "Content-Length");
        response.setHeader("Access-Control-Allow-Headers", "Content-Range");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Allow", "GET");
        return super.preHandle(request, response, o);
    }
    
}
