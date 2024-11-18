package com.book_catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all origins to access the API
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4000") // Replace with your actual allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
                // .allowCredentials(true)
                // .maxAge(3600);
    }
}
