package org.xdq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xdq.demo.common.DemoConstants;

@SpringBootApplication //表示这是一个SpringBoot项目
@ServletComponentScan("org.xdq.demo.common.filter")//让spring扫描指定的包，找过滤器等组件，并加载
public class DemoApp {

    public static void main(String[] args) {

        //获得Spring容器（Spring应用上下文，存放spring管理的各种对象）
        ApplicationContext ctx = SpringApplication.run(DemoApp.class,args);

        //从spring容器中获取环境对象(该对象中存储了当前配置信息)
        Environment env = ctx.getEnvironment();

        //从环境对象中获取上传文件目录，并给DemoConstants.UPLOAD_DIR赋值
        DemoConstants.UPLOAD_DIR = env.getProperty("demo.upload-dir","D:/upload");


    }


//
//    @Bean //表示给SpringBoot应用添加一个对象（即将方法的返回值做为一个受spring管理的对象）
//    public WebMvcConfigurer webMvcConfigurer(){
//        return new WebMvcConfigurer() {
//
//            //在服务端进行跨域设置
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")//允许任何样式的地址请求跨域
//                        .allowedOriginPatterns("*")//允许任何主机来源跨域
//                        .allowedHeaders("*")//允许任何请求头跨域
//                        .allowedMethods("*");//允许任何请求方式(get、post 、。。。。。)跨域
//            }
//        };
//    }

}
