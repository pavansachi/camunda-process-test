package org.camunda.bpm;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.listener.ErrorListener;
import org.example.SpringBootMainApplication;
import org.example.config.AppConfig;
import org.example.model.WeatherData;
import org.example.service.WeatherDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootMainApplication.class, AppConfig.class})
public class CamundaBpmTest {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private WeatherDataService weatherDataService;

	private ProcessInstance processInstance;

	@Autowired
	private ErrorListener errorListener;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test(expected=Exception.class)
	public void testReturnWeatherData() throws Exception {

		Mockito.when(weatherDataService.getWeather("Sydney")).thenReturn(
				new WeatherData("Sydney", 30)
				);

		Map<String, Object> variables = new HashMap<>();
		variables.put("city", "Sydney");
		
		processInstance = runtimeService.
				startProcessInstanceByKey("mProcess", variables)
				;

		Assert.assertThat(runtimeService.createProcessInstanceQuery().count(), is(0L));
	}
	
	@Test
	public void testThrowEx() throws Exception {

		Mockito.when(weatherDataService.getWeather("Sydney")).thenThrow(
				Exception.class);

		Map<String, Object> variables = new HashMap<>();
		variables.put("city", "Sydney");
		
		processInstance = runtimeService.
				startProcessInstanceByKey("mProcess", variables)
				;

		Mockito.verify(errorListener, Mockito.times(1)).execute(Mockito.any());
		
		Assert.assertThat(runtimeService.createProcessInstanceQuery().count(), is(0L));
		
	}

}
