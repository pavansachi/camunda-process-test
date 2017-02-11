package org.example.service;

import org.example.model.WeatherData;
import org.springframework.web.client.RestTemplate;

public class RestWeatherDataService implements WeatherDataService {

	@Override
	public WeatherData getWeather(String city) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
        WeatherData weatherData = restTemplate.getForObject("http://weather.com/api/cities", WeatherData.class);

        return weatherData;
	}

}
