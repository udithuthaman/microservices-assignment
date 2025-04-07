package com.example.studentservice;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableDiscoveryClient  // Enables Eureka Discovery
@EnableFeignClients  // This annotation enables Feign clients
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
	
    // Register RestTemplate as a Bean
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
