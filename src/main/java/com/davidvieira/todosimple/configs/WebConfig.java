package com.davidvieira.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    public void addCorsMapping(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS a todos os endpoints
            .allowedOrigins("http://localhost:8080", "http://127.0.0.1:5500") // Permite origens específicas
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
            .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}
