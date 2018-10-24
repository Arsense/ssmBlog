package com.we.weblog.config;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.web.intercaptor.InstallInterceptor;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  <pre>
 *     拦截器，资源路径配置
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/18 17:13
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.we.weblog.web.controller")
@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true, encoding = "UTF-8")
public class WebMainConig extends WebMvcConfigurationSupport {

    @Resource
    private InstallInterceptor installInterceptor;

    private String LOGIN_URL = "/login1.html" ;

    /**ser
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(installInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/index")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/install");

        //单点登录拦截器
        registry.addInterceptor(ssoInterceptor()).addPathPatterns("/admin/**");
    }


    /**
     * 配置静态资源路径
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //不加的话无法加载静态资源
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


    /**
     * 配置单点登录拦截
     * @return
     */
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
