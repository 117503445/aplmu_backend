package com.wizzstudio.aplmu.config;

import com.wizzstudio.aplmu.entity.ArticleEntity;
import com.wizzstudio.aplmu.security.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class SpringDataRestConfig {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(ArticleEntity.class, User.class);
            }
        };
    }
}