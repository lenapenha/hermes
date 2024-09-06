package com.lenap.hermes.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("Hermes API")
                        .description("")
                        .version("1.0.0")
                ).externalDocs(new ExternalDocumentation()
                        .description("Hermes Documentation")
                        .url("http://localhost:8080/swagger-ui/index.html")
                );
    }
}
