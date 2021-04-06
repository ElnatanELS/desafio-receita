package com.southsystem.desafioreceita.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.southsystem.desafioreceita.batch.notification.JobListener;
import com.southsystem.desafioreceita.batch.notification.StepListener;
import com.southsystem.desafioreceita.batch.processor.ContaToReceitaProcessor;
import com.southsystem.desafioreceita.batch.reader.CSVContaReader;
import com.southsystem.desafioreceita.batch.writer.CSVContaWriter;
import com.southsystem.desafioreceita.entidades.Conta;


/**
 * Clase de configuração do Job Conta
 * @author Elnatan Emanuel
 *
 */



@EnableBatchProcessing
@Configuration
public class ContaJob {
	/**
	 * Quantas linhas será  porcessadas antes da transação
	 */
	
	final Integer QTD_DE_PROCESSOS_POR_TRANSACAO = 500;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CSVContaReader csvReader;

	@Autowired
	private JobListener jobListener;

	@Autowired
	private StepListener stepListener;

	@Autowired
	private ContaToReceitaProcessor contaToReceitaProcessor;

	@Autowired
	private CSVContaWriter csvWriter;
	/**
	 *  metodo que instancia o Job
	 * @return Job
	 */

	@Bean
	public Job jobConta() {
		return jobBuilderFactory.get("jobConta")
				.listener(jobListener)
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}
	
	/**
	 * Metodo que instacia o Step
	 * 
	 * @return Step
	 */
	@Bean
	public Step step1() {
		return stepBuilderFactory
				.get("step1")
				.listener(stepListener)
				.<Conta, Conta>chunk(QTD_DE_PROCESSOS_POR_TRANSACAO)
				.reader(csvReader)
				.processor(contaToReceitaProcessor)
				.writer(csvWriter).build();
	}

}
