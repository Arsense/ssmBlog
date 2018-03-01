package com.we.weblog.intercaptor;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import com.vue.adminlte4j.model.UIModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vue.adminlte4j.model.UIModel.IS_LOGIN;

/**
 * web页面配置类，拦截器地址在此注册
 */
@ControllerAdvice
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{


    private String LOGIN_URL = "/login1.html" ;


    @Override
    public void addInterceptors(InterceptorRegistry registry){

        // kisso 拦截器配置



        registry.addInterceptor(setSSOInterceptor()).excludePathPatterns("/login1.html") ;
     //   super.addInterceptors(registry);

    }

    /**
     *  配置拦截器未登陆的挑战
     * @return
     */
    public  SSOSpringInterceptor setSSOInterceptor(){
         SSOSpringInterceptor interceptor = new SSOSpringInterceptor();

        SSOConfig.getInstance().setLoginUrl(LOGIN_URL);

        SSOConfig.getInstance().setLogoutUrl("/logout.html");
        interceptor.setHandlerInterceptor(new SSOHandlerInterceptor() {
            @Override
            public boolean preTokenIsNullAjax(HttpServletRequest request, HttpServletResponse response) {
                try {
                    String rJson = String.format("{\"%s\" : %s , \"%s\" : \"%s\" }" ,  IS_LOGIN , false  , UIModel.LOGIN_URL , LOGIN_URL) ;
                    response.setContentType("application/json");
                    response.getOutputStream().write(rJson.getBytes());

                } catch (IOException e) {
                    // to do nothing
                }
                return false;

            }

            @Override
            public boolean preTokenIsNull(HttpServletRequest request, HttpServletResponse response) {
                return true;
            }
        });

        return interceptor;
    }



}
