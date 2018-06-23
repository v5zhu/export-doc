/*
 *
 *                    .___               __
 *   _____   ____   __| _/____   _______/  |_
 *  /     \ /  _ \ / __ |/ __ \ /  ___/\   __\
 * |  Y Y  (  <_> ) /_/ \  ___/ \___ \  |  |
 * |__|_|  /\____/\____ |\___  >____  > |__|
 *       \/            \/    \/     \/
 *
 * -------- By Liuyihua. -------- '''' -------
 *
 * @url http://www.modest.ren
 */

package com.zhuxl.exportdoc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger2配置文件
 * 扩展了token到接口中
 */
@Configuration
@EnableSwagger2
@SuppressWarnings("all")
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Access-Token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/v1/.*"))
                .build()
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档与调试页面")
                .description("这是一个给客户端开发人员查看和调试服务端接口的测试文档平台")
                .version("1.0.0")
                .build();
    }

}
