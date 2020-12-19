package ru.greenatom.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.greenatom.demo.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * todo  подробно изучить параметры title,description,licenseUrl,contact
     *
     * **/
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API Piece Of Paper")
                .description("\"Spring Boot REST API for a piece of paper\"")
                .version("1.0.2")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Project", "https://github.com/grinprogectspop/Piece_of_paper", " grinprogectspop@gmail.com"))
                .contact(new Contact("Ветви", "https://github.com/grinprogectspop/Piece_of_paper/network", " grinprogectspop@gmail.com"))
                .contact(new Contact("ECM.Spec.Req", "https://docs.google.com/document/d/1Cl_ODNYa6SD3kMDGC9Oe_ZAThl4ZbTUfSrDesAwvpw0/edit", " grinprogectspop@gmail.com"))
                .contact(new Contact("API", "https://docs.google.com/document/d/1a_dS6EJJqt3a9z35XfsW9CUnLNWlNTuIuOnUZhHX0q0/edit?usp=sharing", " grinprogectspop@gmail.com"))
                .contact(new Contact("Доска задач", "https://trello.com/invite/b/a5RVPYil/0a8bf0486a2dfc4731471282234dd1f8/проэкт", " grinprogectspop@gmail.com"))
                .contact(new Contact("ER-диаграмма", "https://docs.google.com/document/d/1Cl_ODNYa6SD3kMDGC9Oe_ZAThl4ZbTUfSrDesAwvpw0/edit", " grinprogectspop@gmail.com"))
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}