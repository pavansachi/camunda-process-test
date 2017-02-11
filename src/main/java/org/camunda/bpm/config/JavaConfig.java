package org.camunda.bpm.config;

import org.camunda.bpm.service.DataService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.camunda.bpm")
public class JavaConfig {

	@Bean
	public DataService dataService() {
		
		return Mockito.mock(DataService.class);
		
	}
	
}
