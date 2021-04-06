package com.southsystem.desafioreceita.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.southsystem.desafioreceita.batch.mapper.ContaFieldSetMapper;
import com.southsystem.desafioreceita.entidades.Conta;

@Component
public class CSVContaReader extends FlatFileItemReader<Conta> {
	
	private Resource inputResource = new FileSystemResource("C:/entrada/in.csv");

	public CSVContaReader() {
		setResource(inputResource);
		setLinesToSkip(1);
		setLineMapper(lineMapper());
	}

	@Bean
	public LineMapper<Conta> lineMapper() {
		DefaultLineMapper<Conta> lineMapper = new DefaultLineMapper<Conta>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setNames(new String[] { "agencia", "conta", "saldo", "status" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3 });
		ContaFieldSetMapper fieldSetMapper = new ContaFieldSetMapper();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;

	}
}
