package com.babak.gerimedicaassignment;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GerimedicaAssignmentOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI();
    }
}
