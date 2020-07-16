package com.ncu.doctor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ncu.doctor.config
 *
 * @author hzh
 * Created by  2020/7/12.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("access_token")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ncu.doctor.controller"))
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("医生站服务API")
                .termsOfServiceUrl("")
                .contact(new Contact("ITBoy-China", "https://github.com/ITBoy-China", "vicbelieve@outlook.com"))
                .version("1.0")
                .build();
    }
}
