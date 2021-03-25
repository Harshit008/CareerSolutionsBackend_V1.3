package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zensar.config.SwaggerConfiguration;
@CrossOrigin
@Import(SwaggerConfiguration.class)
@SpringBootApplication
public class CareerSolutionsApplication {
  
	public static void main(String[] args) {
		SpringApplication.run(CareerSolutionsApplication.class, args);
	}
	
//	 @Bean
//	   public Docket productApi() {
//	      return new Docket(DocumentationType.SWAGGER_2).select()
//	         .apis(RequestHandlerSelectors.basePackage("com.zensar")).build();
//	   }
	
//	@Bean
//	public CorsFilter corsFilter() {
//			CorsConfiguration corsConfiguration = new CorsConfiguration();
//			corsConfiguration.setAllowCredentials(true);
//			corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//			
//			corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
//					"Accept","Authorization","Origin, Accept","X-Requested-With",
//					"Access-Control-Request-Method", "Access-Control-Request-Headers"));
//			
//			corsConfiguration.setExposedHeaders(Arrays.asList("Origin","Content-Type","Accept","Authorization"
//					,"Access-Control-Allow-Origin","Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//			corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
//			
//			UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource= new UrlBasedCorsConfigurationSource();
//			
//			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//			return new CorsFilter(urlBasedCorsConfigurationSource);
//	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	  return new WebMvcConfigurer() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	      registry.addMapping("/**").allowedOrigins("http://localhost:4200");
	    }
	  };
	}

}
