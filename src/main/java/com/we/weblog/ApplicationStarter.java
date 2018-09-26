package com.we.weblog;


import com.vue.adminlte4j.support.FileChangeListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ApplicationStarter {

    public static void main(String[] args) {
        //动态自动更新 每一秒更新一次
        configFileChangeListener();
        SpringApplication.run(ApplicationStarter.class,args);
    }

    private static void configFileChangeListener() {
        FileChangeListener fileChangeListener = FileChangeListener.getInstance();

        Path distLibPath = Paths.get("src" , "main" ,"static" , "dist" , "lib") ;
        Path targetLibPath = Paths.get("target" , "classes" , "META-INF" , "resources" , "lib") ;
        Path vueAdminlteJs = Paths.get("vue-adminlte","dist","js","vue-adminlte.min.js") ;
        Path baseCss = Paths.get("vue-adminlte","dist","css","base.css") ;

        fileChangeListener.listen(distLibPath , "base.js").to(targetLibPath , "base.js") ;
        fileChangeListener.listen(distLibPath , "adminlte.js").to(targetLibPath , "adminlte.js") ;
        fileChangeListener.listen(distLibPath , "lib.js").to(targetLibPath , "lib.js") ;
        fileChangeListener.listen(distLibPath , vueAdminlteJs).to(targetLibPath , vueAdminlteJs) ;
        fileChangeListener.listen(distLibPath , baseCss).to(targetLibPath , baseCss) ;
        fileChangeListener.autoConfig(FileChangeListener.SPRING_BOOT).start();

    }
}
