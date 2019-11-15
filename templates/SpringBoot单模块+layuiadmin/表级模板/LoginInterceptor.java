package com.project.platform.interceptor;

import com.project.platform.util.JavaWebToken;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * JWT登陆权限拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证Jwt字符串是否合法,不合法时,跳转到登陆页
        if (request.getSession().getAttribute("jwt") != null) {
            String jwt = String.valueOf(request.getSession().getAttribute("jwt"));
            Map jwtMap = JavaWebToken.parserJavaWebToken(jwt);
            if (jwtMap == null) {
                response.sendRedirect("/");
                return false;
            }
        } else {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
