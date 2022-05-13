package com.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableHystrix
public class CheckInApplication {
	
	    
	
	    @LoadBalanced
	    @Bean	
		public RestTemplate restTemplate()
		{
			return new RestTemplate();
		}

	public static void main(String[] args) {
		SpringApplication.run(CheckInApplication.class, args);
	}

}
