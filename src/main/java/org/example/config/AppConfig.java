package org.example.config;

import org.camunda.bpm.listener.ErrorListener;
import org.example.service.WeatherDataService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.camunda.bpm", "org.example"})
public class AppConfig {

	@Bean
	public WeatherDataService weatherDataService() {
		
		return Mockito.mock(WeatherDataService.class);
		
	}
	
	@Bean
	public ErrorListener errorListener() {
		
		return Mockito.mock(ErrorListener.class);
		
	}
	
}
