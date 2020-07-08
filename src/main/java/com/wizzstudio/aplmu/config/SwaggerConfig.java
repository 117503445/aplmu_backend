package com.wizzstudio.aplmu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${swaggerhost}")
    private String swaggerHost;
    /**
     * 当前版本
     */
    @Value("${app.version}")
    private String version;
    /**
     * 打包时间
     */
    @Value("${app.build.time}")
    private String buildTime;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2).host(swaggerHost)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().securitySchemes(Collections.singletonList(securitySchemes())).securityContexts(Collections.singletonList(securityContext())).apiInfo(new ApiInfoBuilder()
                        .title("aplmu")
                        .description("aplmu 后端")
                        .version(String.format("%s %s UTC+0", version, buildTime))
                        .contact(new Contact("Qht", "https://www.117503445.top", "t117503445@gmail.com"))
                        .build());
    }

    private SecurityScheme securitySchemes() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("Authorization", authorizationScopes));
    }
}