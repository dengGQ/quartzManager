package com.dgq.config.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*@Configuration
@EnableWebMvc*/
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("-----------------");
		registry.addMapping("/cors/**")
		.allowedMethods("GET");
	}
}
