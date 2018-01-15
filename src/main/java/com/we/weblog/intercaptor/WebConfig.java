package com.we.weblog.intercaptor;

import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.Arrays;

/**
 * web页面配置类，拦截器地址在此注册
 */
@ControllerAdvice
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry){

        // kisso 拦截器配置
        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/get_app_info");
        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/get_table_data");
        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/admin/edit.html");
    }



}
