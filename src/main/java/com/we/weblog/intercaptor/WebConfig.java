package com.we.weblog.intercaptor;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web页面配置类，拦截器地址在此注册
 */
@ControllerAdvice
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    private boolean urlLoadStatus = true;
    @Override
    public void addInterceptors(InterceptorRegistry registry){

        // kisso 拦截器配置
        if(urlLoadStatus){
            SSOConfig.getInstance().setLoginUrl("/login1.html");
            SSOConfig.getInstance().setLogoutUrl("/logout.html");
            urlLoadStatus = false;
        }


        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/admin/**");
        super.addInterceptors(registry);

    }



}
