package com.southsystem.desafioreceita;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.southsystem.desafioreceita.batch.ExecutorJob;
import com.southsystem.desafioreceita.services.ArquivoService;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DesafioReceitaApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(DesafioReceitaApplication.class, args);
	}
	
}
