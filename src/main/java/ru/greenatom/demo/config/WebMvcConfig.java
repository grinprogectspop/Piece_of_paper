package ru.greenatom.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File fileUploadPath = new File(uploadPath);
        if (!fileUploadPath.exists()) fileUploadPath.mkdir();

        registry.addResourceHandler("/**")
                .addResourceLocations("file:///" + uploadPath + "/");


        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

    }
}

