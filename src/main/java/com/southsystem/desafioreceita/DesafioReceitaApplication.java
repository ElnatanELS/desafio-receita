package com.southsystem.desafioreceita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DesafioReceitaApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(DesafioReceitaApplication.class, args);
	}
	
}
