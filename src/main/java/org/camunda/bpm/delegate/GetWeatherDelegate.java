/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.model.WeatherData;
import org.example.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetWeatherDelegate implements JavaDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetWeatherDelegate.class);

	@Autowired
	private WeatherDataService weatherDataService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String city = (String) execution.getVariable("city");

		try {
			WeatherData weatherData = weatherDataService.getWeather(city);

			String.format("Weather data -> %s : %s", city, weatherData.getTemperature());

			execution.setVariable("DATA_AVAILABLE", true);
		}
		catch (Exception e) {

			LOGGER.error("Weather Data not available");
			execution.setVariable("DATA_AVAILABLE", false);
		}

	}

}
