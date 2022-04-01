package com.longsys.export.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.longsys.export.constant.Knife4jConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * knife4j配置类
 *
 * @author huan.yang
 * @date 2022-03-29
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).groupName(Knife4jConstant.GROUP_NAME).select().paths(PathSelectors.any()).apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build().extensions(openApiExtensionResolver.buildSettingExtensions());

    }


    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(Knife4jConstant.TITLE).description(Knife4jConstant.DESCRIPTION).version(Knife4jConstant.VERSION).termsOfServiceUrl(Knife4jConstant.SERVER_URL_TIE).contact(new Contact(Knife4jConstant.NAME, Knife4jConstant.URL, Knife4jConstant.EMAIL)).build();
    }

}
