package com.southsystem.desafioreceita.batch.configuracao;

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

import com.southsystem.desafioreceita.batch.leitor.CSVContaLeitor;
import com.southsystem.desafioreceita.batch.notificacao.JobListener;
import com.southsystem.desafioreceita.batch.notificacao.StepListener;
import com.southsystem.desafioreceita.entidades.Conta;

@EnableBatchProcessing
@Configuration
public class ContaJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CSVContaLeitor csvLeitor;

	@Autowired
	private JobListener jobListener;

	@Autowired
	private StepListener stepListener;

	private Resource outputResource = new FileSystemResource("C:/output/outputData.csv");

	@Bean
	public FlatFileItemWriter<Conta> writer() {
		// Create writer instance
		FlatFileItemWriter<Conta> writer = new FlatFileItemWriter<>();

		// Set output file location
		writer.setResource(outputResource);

		// All job repetitions should "append" to same output file
		writer.setAppendAllowed(true);

		// Name field values sequence based on object properties
		writer.setLineAggregator(new DelimitedLineAggregator<Conta>() {
			{
				setDelimiter(";");
				setFieldExtractor(new BeanWrapperFieldExtractor<Conta>() {
					{
						setNames(new String[] { "agencia", "conta", "saldo", "status" });
					}
				});
			}
		});
		return writer;
	}

	@Bean
	public Job jobConta() {
		return jobBuilderFactory.get("jobConta")
				.listener(jobListener)
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory
				.get("step1")
				.listener(stepListener)
				.<Conta, Conta>chunk(300)
				.reader(csvLeitor)
				.writer(writer()).build();
	}

}
