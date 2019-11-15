package com.github.wb322.config;

import com.github.wb322.entity.Strategy;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wubo
 * @date 2019年11月12日 15:46
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String templatesPath = "file:" +Strategy.templatesPath;
        registry.addResourceHandler("/file/**").addResourceLocations(templatesPath);
    }
}
