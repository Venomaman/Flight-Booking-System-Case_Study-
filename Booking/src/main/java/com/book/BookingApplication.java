package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class BookingApplication {
	
//	 @Bean
//	  public WebClient.Builder getWebClientBuilder(){ return
//	  WebClient.builder(); }
//	
	    @LoadBalanced
	    @Bean
		public RestTemplate restTemplate()
		{
			return new RestTemplate();
		}

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}
	


}
