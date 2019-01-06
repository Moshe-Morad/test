package com.moshe.final_project2;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;




@SpringBootApplication
@EnableAsync
@EnableScheduling
public class FinalProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(FinalProject2Application.class, args);
	}
	
	
	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	 
	 
	 
//	 @Bean("findCompanyById")
//	 public Executor taskExecutor() {
//	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//	        executor.setCorePoolSize(2);
//	        executor.setMaxPoolSize(2);
//	        executor.setQueueCapacity(500);
//	        executor.setThreadNamePrefix("ThraedService");
//	        executor.initialize();
//	        return executor;
//	    }
	 
}
