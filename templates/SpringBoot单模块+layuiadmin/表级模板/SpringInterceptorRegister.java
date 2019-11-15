package com.project.platform.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 注册所有的拦截器、静态资源
 */
@Configuration
public class SpringInterceptorRegister extends WebMvcConfigurationSupport {

    @Value("${materialRealPath}")
    private String materialRealPath;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //登陆拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/templates/**")
                .excludePathPatterns("/material/**")
                .excludePathPatterns("/*");

        //菜单权限拦截器
        registry.addInterceptor(new MenuPermission())
                .addPathPatterns("/**/**")
                .excludePathPatterns("/**/get**")
                .excludePathPatterns("/**/find**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/templates/**")
                .excludePathPatterns("/*");   //首页

        super.addInterceptors(registry);
    }

    //注册静态资源，不然访问不了
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

        //路径下的图片映射
        registry.addResourceHandler("/material/**").addResourceLocations("file:" + materialRealPath);
        super.addResourceHandlers(registry);
    }
}
