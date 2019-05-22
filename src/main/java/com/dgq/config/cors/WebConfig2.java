package com.dgq.config.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig2 {
	
//	@Bean
	public WebMvcConfigurer corsConfigurer() {
		System.out.println("--------------------");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/cors/**")
					.allowedMethods("GET");
			}
		};
	}
}
