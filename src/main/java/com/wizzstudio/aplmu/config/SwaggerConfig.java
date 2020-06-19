package com.wizzstudio.aplmu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Import(SpringDataRestConfiguration.class)
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

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("JWT Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());


        return new Docket(DocumentationType.SWAGGER_2).host(swaggerHost)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars).apiInfo(new ApiInfoBuilder()
                        .title("aplmu")
                        .description("aplmu 后端")
                        .version(String.format("%s %s UTC+0", version, buildTime))
                        .contact(new Contact("Qht", "https://www.117503445.top", "t117503445@gmail.com"))
                        .build());
    }
}