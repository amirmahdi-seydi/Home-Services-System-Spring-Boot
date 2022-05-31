package ir.maktab.homeservice;

import ir.maktab.homeservice.service.CustomerService;
import ir.maktab.homeservice.service.impl.CustomerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HomeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:3000","http://localhost:8080")
						.allowCredentials(true);
			}
		};
	}
}
