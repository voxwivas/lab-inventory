package com.labfolder.labexperimentinventory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.labfolder.labexperimentinventory"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }
}

