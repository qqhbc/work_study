package com.yc.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: yinchao
 * @date 2019/7/1
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：接口文档")
                        .description("描述：用于管理所有模块接口文档...")
                        .contact(new Contact("hty", "https://www.baidu.com", "1162454662@qq.com"))
                        .version("版本号:2.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yc.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
