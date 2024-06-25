package jobplatform;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebConfig implements WebMvcConfigurer {
	
		// CORS 문제 해결
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("http://localhost:8080")
					.allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT").allowedHeaders("Authorization", "Content-Type")
					.exposedHeaders("Custom-Header").allowCredentials(true).maxAge(3600);
		}
		
		
}
