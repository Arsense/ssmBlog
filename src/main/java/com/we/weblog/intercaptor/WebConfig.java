package com.we.weblog.intercaptor;

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
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{


    private SecurityInterceptor interceptor;



    @Autowired
    public WebConfig(SecurityInterceptor securityInterceptor){
        super();
        this.interceptor = securityInterceptor;
    }

    //registry.addInterceptor可以通过此方法添加拦截器, 可以是spring提供的或者自己添加的
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //配置登录拦截器拦截路径 
        registry.addInterceptor(interceptor).addPathPatterns("/get_app_info");
        registry.addInterceptor(interceptor).addPathPatterns("/get_table_data");
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
