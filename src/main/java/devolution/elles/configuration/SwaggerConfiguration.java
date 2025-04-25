package devolution.elles.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Elles User API")
                        .version("0.01")
                        .description("API For Elles Application"));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // autorise tous les endpoints
                        .allowedOrigins("http://localhost:4200") // autorise ton front
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // méthodes permises
                        .allowedHeaders("*");
            }
        };
    }
}
