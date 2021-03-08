package com.tp.cozubu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String SAVE_PATH;             // 파일 업로드 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //webjars 버전 없이 관리하기 위한 설정
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);

        //resouces 위치
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/static/img/**")
                .addResourceLocations("file:///"+SAVE_PATH)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver())
        ;
        registry.addResourceHandler("/webponent.*")
                .addResourceLocations("/resources/webponent/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
