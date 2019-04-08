package com.we.weblog;

import com.vue.adminlte4j.support.FileChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author tangwei9
 * @date 2019-03-28 13:49 2019-03-28 13:50
 */
@SpringBootApplication
public class ApplicationStarter {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) {
        //动态自动更新 每一秒更新一次
        configFileChangeListener();
        ApplicationContext context = SpringApplication
                .run(ApplicationStarter.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        //输入访问链接
        LOG.info("Clay started at http://localhost:" + serverPort);
    }


    /**.
     * .
     * 页面自动更新前端资源 改完代码后刷新即可
     */
    private static void configFileChangeListener() {
        FileChangeListener fileChangeListener = FileChangeListener.getInstance();
        Path distLibPath = Paths.get("src", "main", "static", "jequery", "lib");
        Path targetLibPath = Paths.get("target", "classes", "META-INF", "resources", "lib");
        Path vueAdminlteJs = Paths.get("vue-adminlte", "jequery", "js", "vue-adminlte.min.js");
        Path baseCss = Paths.get("vue-adminlte", "jequery", "css", "base.css");

        fileChangeListener.listen(distLibPath, "base.js")
                          .to(targetLibPath, "base.js");
        fileChangeListener.listen(distLibPath, "adminlte.js")
                          .to(targetLibPath, "adminlte.js");
        fileChangeListener.listen(distLibPath, "lib.js").to(targetLibPath, "lib.js");
        fileChangeListener.listen(distLibPath, vueAdminlteJs).to(targetLibPath, vueAdminlteJs);
        fileChangeListener.listen(distLibPath, baseCss).to(targetLibPath, baseCss);
        fileChangeListener.autoConfig(FileChangeListener.SPRING_BOOT).start();

    }
}
