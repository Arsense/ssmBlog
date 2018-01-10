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
    
    
//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        //下面都是设置HTTP响应头
//        CorsConfiguration configuration = new CorsConfiguration();
//        //#允许Cookie跨域，在做登录校验的时候有用
//        configuration.setAllowCredentials(true);
//        //允许访问的请求头
//        configuration.addAllowedHeader("*");
//        //#允许提交请求的方法，*表示全部允许
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","POST","DELETE"));
//        //允许访问的源
//        configuration.addAllowedOrigin("*");
//        source.registerCorsConfiguration("/**",configuration);
//        return new CorsFilter(source);
//
//
//
//    }




}
