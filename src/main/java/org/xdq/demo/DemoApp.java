package org.xdq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication //表示这是一个SpringBoot项目
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class,args);
    }




    @Bean   // 表示给SpringBoot应用添加一个对象（即将方法的返回值做为一个受spring管理的对象)
    public WebMvcConfigurer  webMvcConfigurer(){
        return new WebMvcConfigurer() {
            // 在服务端进行跨域设置
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry
                        .addMapping("/**")//允许任何样式的地址请求跨域
                        .allowedOriginPatterns("*")//允许任何主机来源跨域
                        .allowedHeaders("*")//允许任何请求头跨域
                        .allowedMethods("*");//允许任何请求方式跨域



            }
        };


    }


}
