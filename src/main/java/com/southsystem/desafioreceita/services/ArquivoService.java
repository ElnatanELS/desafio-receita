package com.southsystem.desafioreceita.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class ArquivoService {
	
	//faz a leitura do arquivo csv com os dados da UBS e retornar uma lista de UBSs
		public void readFile() {		
			
			
			Path path = Paths.get("c:/entrada/in.txt");
			
			try(BufferedReader reader = Files.newBufferedReader(path,Charset.forName("UTF-8"))){
				
				String line = null;
				
				while((line = reader.readLine()) != null) {		
					
					System.out.println(line);
									
										
									
				}
				
				
			}catch(IOException e) {			
				
				throw new RuntimeException("Erro ao fazer a leitura do arquivo");
				
			}
			
		}	

}
