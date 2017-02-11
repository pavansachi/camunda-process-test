package org.camunda.bpm.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorListener implements JavaDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorListener.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		LOGGER.info("Error Listener called");
	}

}
