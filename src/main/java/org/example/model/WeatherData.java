package org.example.model;

public class WeatherData {

	public WeatherData(final String city, 
			final int temperature) {
		
		this.city = city;
		this.temperature = temperature;
	}
	
	private String city;
	private int temperature;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
}
