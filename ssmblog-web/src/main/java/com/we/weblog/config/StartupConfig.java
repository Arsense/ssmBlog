package com.we.weblog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.Unsafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * <pre>
 *     启动加载配置主题等
 * </pre>
 *
 *
 * @author Clay
 * @date 2018/10/23 17:25
 */
@Configuration
public class StartupConfig implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(StartupConfig.class);

//    @Resource
//    private OptionsService optionsService;

    /**
     * Spring 加载完后启动
     * @param applicationStartedEvent
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        //TODO 启动加载系统配置和主题
        this.loadOptions();
    }


    private void loadCurrenTheme(){
        //TODO 这里加载主题吧

    }

    /**
     * 加载设置选项
     */
    private void loadOptions() {
//        Map<String, String> options = optionsService.findAllOptions();
//        if (options != null && !options.isEmpty()) {
//            BaseConfigUtil.OPTIONS = options;
//        }
    }

}
