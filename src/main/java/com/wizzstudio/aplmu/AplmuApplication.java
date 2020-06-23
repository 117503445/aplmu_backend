package com.wizzstudio.aplmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@SpringBootApplication
@EnableJpaAuditing
public class AplmuApplication {
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new CallbackServer(), "/");// ServletName默认值为首字母小写，即myServlet
//    }

    public static void main(String[] args) {
        SpringApplication.run(AplmuApplication.class, args);
    }

}
