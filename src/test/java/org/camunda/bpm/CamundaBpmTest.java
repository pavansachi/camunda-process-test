package org.camunda.bpm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.example.SpringBootMainApplication;
import org.example.config.AppConfig;
import org.example.service.DataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
	private DataService dataService;

	private ProcessInstance processInstance;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testDataServiceReachable() {

		Mockito.when(dataService.isReachable()).thenReturn(true);

		processInstance = runtimeService.startProcessInstanceByKey("mProcess");

		Assert.assertNotNull(processInstance);

		assertThat(runtimeService.createProcessInstanceQuery().count(), is(0L));
	}

//	@Test
	@Ignore
	public void testDataServiceNotReachable() {

		Mockito.when(dataService.isReachable()).thenReturn(false);
		
		processInstance = runtimeService.startProcessInstanceByKey("mProcess");
		
		Assert.assertNotNull(processInstance);

		Execution execution = runtimeService.createExecutionQuery()
				  .processInstanceId(processInstance.getId()).activityId("Task_wait").singleResult();
		
		runtimeService.signal(execution.getId());
		
		assertThat(runtimeService.createProcessInstanceQuery().count(), is(0L));
	}

}
