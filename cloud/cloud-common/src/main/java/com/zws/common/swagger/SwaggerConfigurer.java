package com.zws.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/3
 */
@EnableSwagger2
@Configuration
public class SwaggerConfigurer {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2).
                useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zws"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(productApiInfo())
                ;

    }


    private ApiInfo productApiInfo() {
        return new ApiInfoBuilder()
                .title("cloud APIs")
                .description("")
                .termsOfServiceUrl("")
                .contact(new Contact("zws", "", "2848392861@qq.com"))
                .version("1.0")
                .build();
    }
}
