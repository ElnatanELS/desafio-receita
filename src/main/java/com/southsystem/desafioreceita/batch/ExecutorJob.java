package com.southsystem.desafioreceita.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Classe que inicializa o job do Bacth 
 * @author Elnatan Emanuel
 *
 */
@Component
public class ExecutorJob {
	@Autowired
    private Job job;
 
    @Autowired
    private JobLauncher jobLauncher;
 
    public JobExecution execute() throws Exception {
        JobParameters params = new JobParametersBuilder()
        		.addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        return jobLauncher.run(job, params);
    }

}
