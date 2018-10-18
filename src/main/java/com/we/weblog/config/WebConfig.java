package com.we.weblog.config;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import com.vue.adminlte4j.model.UIModel;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
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

    private String LOGIN_URL = "/login1.html" ;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(ssoInterceptor()).addPathPatterns("/admin/**");
    }

    @Bean(autowire = Autowire.BY_NAME )
    public SSOSpringInterceptor ssoInterceptor() {

        SSOConfig.getInstance().setLoginUrl(LOGIN_URL);
        SSOConfig.getInstance().setLogoutUrl("/logout.html");

        SSOSpringInterceptor springSSOInterceptor =  new SSOSpringInterceptor();
        springSSOInterceptor.setHandlerInterceptor(new SSOHandlerInterceptor() {
            @Override public boolean preTokenIsNullAjax(HttpServletRequest request, HttpServletResponse response) {
                try {
                    UIModel uiModel = UIModel.success().isLogin(false).setLoginUrl(LOGIN_URL);
                    response.getWriter().write(uiModel.toJsonString());

                } catch (IOException e) {
                    // to do nothing
                }
                return false;
            }
            @Override public boolean preTokenIsNull(HttpServletRequest request
                    ,HttpServletResponse response) {
                return true;
            }
          });
        return springSSOInterceptor;
    }






}
