package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zensar.config.SwaggerConfiguration;

@CrossOrigin
@Import(SwaggerConfiguration.class)
@SpringBootApplication
@Async
public class CareerSolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerSolutionsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://career-solutions-job-portal.s3-website.ap-south-1.amazonaws.com");
			}
		};
	}

}
