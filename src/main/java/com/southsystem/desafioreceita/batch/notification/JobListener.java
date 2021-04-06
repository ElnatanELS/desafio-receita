package com.southsystem.desafioreceita.batch.notification;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Classe que gera a notificao da execução do JOB
 * @author Elnatan Emanuel
 *
 */
@Component
public class JobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		 System.out.println("Iniciou o Job " + jobExecution.getId() + " (" + jobExecution.getStatus().name() + ")");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		 System.out.println("Finalizou o Job " + jobExecution.getId() + " (" + jobExecution.getStatus().name() + ")");

	}

}
