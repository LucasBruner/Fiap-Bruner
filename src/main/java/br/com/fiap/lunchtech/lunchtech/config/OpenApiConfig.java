package br.com.fiap.lunchtech.lunchtech.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI LunchTech() {
        return new OpenAPI().info(
                new Info().title("Lunchtech API")
                        .description("Projeto desenvolvido durante o curso Spring MVC")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://github.com/LucasBruner")));
    }

    @Bean
    public GroupedOpenApi loginApiV1() {
        return GroupedOpenApi.builder()
                .group("api-v1")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi loginApiV2() {
        return GroupedOpenApi.builder()
                .group("api-v2")
                .pathsToMatch("/v2/**")
                .build();
    }
}
