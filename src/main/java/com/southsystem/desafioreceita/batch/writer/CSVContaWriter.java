package com.southsystem.desafioreceita.batch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.southsystem.desafioreceita.entidades.Conta;

/**
 * Classe que gera o arquivo csv das contas 
 * @author Elnatan Emanuel
 *
 */

@Component
public class CSVContaWriter extends FlatFileItemWriter<Conta> {
	
	private Resource outputResource = new FileSystemResource("C:/saida/ContasAutalizadas.csv");
	
	public CSVContaWriter() {
		setResource(outputResource);
		setAppendAllowed(true);
		setHeaderCallback(writer -> writer.write("agencia;conta;saldo;status;resultado"));
		setLineAggregator(delimeted());
	}
	@Bean
	public DelimitedLineAggregator<Conta> delimeted() {
		DelimitedLineAggregator<Conta> delimeted = new DelimitedLineAggregator<Conta>();
		delimeted.setDelimiter(";");
		BeanWrapperFieldExtractor<Conta> fieldExtractor = new BeanWrapperFieldExtractor<Conta>();
		fieldExtractor.setNames(new String[] { "agencia", "conta", "saldo", "status", "resultado" });
		delimeted.setFieldExtractor(fieldExtractor);
		return delimeted;
	}

}
