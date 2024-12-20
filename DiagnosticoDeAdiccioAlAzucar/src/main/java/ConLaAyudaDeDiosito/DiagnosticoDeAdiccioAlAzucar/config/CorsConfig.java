package ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Cambia * por la URL permitida si es específica
                . allowedMethods("GET", "POST", "PUT", "DELETE", "PATH") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Headers permitidos
    }
}