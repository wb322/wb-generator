package com.project.platform.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 菜单权限拦截器
 * @author: zhupeng
 * @date: 2019-01-04 10:59
 */
public class MenuPermission implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String pathStr = request.getServletPath();

        System.out.println("-------------菜单权限拦截器-------------");
        return true;
    }

    //该方法在controller方法执行之后调用，可以对modelAndView进行处理，比如添加属性什么的
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    //最后执行，在controller渲染视图完成之后，做清理工作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
