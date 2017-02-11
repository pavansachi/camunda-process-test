package org.example.service;

import org.example.model.WeatherData;

public interface WeatherDataService {

	public WeatherData getWeather(String city) throws Exception;
}
