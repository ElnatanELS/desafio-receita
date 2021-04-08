package com.southsystem.desafioreceita;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.southsystem.desafioreceita.batch.configuration.ContaJob;
import com.southsystem.desafioreceita.batch.notification.JobListener;
import com.southsystem.desafioreceita.batch.notification.StepListener;
import com.southsystem.desafioreceita.batch.processor.ContaToReceitaProcessor;
import com.southsystem.desafioreceita.batch.reader.CSVContaReader;
import com.southsystem.desafioreceita.batch.writer.CSVContaWriter;


@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class })
@ContextConfiguration(classes = { ContaJob.class, StepListener.class, CSVContaReader.class, CSVContaWriter.class, JobListener.class, ContaToReceitaProcessor.class })

public class ContaJobTestUnit {

	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
	
	
	/**
	 * Teste para validar se o job está executrando
	 * @throws Exception
	 */
	@Test
	public void whenJobExecuted_thenSuccess() throws Exception {

	    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
	  
	    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
	
	/**
	 * Teste para validar se o job está executrando
	 * @throws Exception
	 */
	@Test
	public void whenStepExecuted_thenSuccess() throws Exception {
		
		JobExecution jobExecution = jobLauncherTestUtils.launchStep(
			      "step1" );
		
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}
}
