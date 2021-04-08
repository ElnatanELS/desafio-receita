package com.southsystem.desafioreceita.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
	
	@Autowired
    private Job job;
 
    @Autowired
    private JobLauncher jobLauncher;
    
    @GetMapping
    public BatchStatus gerarArquivos() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
    	JobParameters params = new JobParametersBuilder()
        		.addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
    	JobExecution jobExecution = jobLauncher.run(job, params);
    	
    	return jobExecution.getStatus();
        
        
    }

}
