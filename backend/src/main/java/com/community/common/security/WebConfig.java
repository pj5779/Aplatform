package com.community.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final LoginCheckInterceptor loginCheckInterceptor;

	public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
		this.loginCheckInterceptor = loginCheckInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/secure/**"); // 여기에 적용할 URL 패턴을 지정
	}

	// CORS 문제 해결
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080")
				.allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT").allowedHeaders("Authorization", "Content-Type")
				.exposedHeaders("Custom-Header").allowCredentials(true).maxAge(3600);
	}
}