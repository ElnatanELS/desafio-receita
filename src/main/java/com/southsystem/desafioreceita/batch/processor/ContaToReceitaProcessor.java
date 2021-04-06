package com.southsystem.desafioreceita.batch.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.southsystem.desafioreceita.entidades.Conta;
import com.southsystem.desafioreceita.services.ReceitaService;

@Component
public class ContaToReceitaProcessor implements ItemProcessor<Conta, Conta> {

	@Override
	public Conta process(Conta item) throws Exception {
		Conta contaProcess = new Conta();
		
		contaProcess.setAgencia(item.getAgencia());
		contaProcess.setConta(item.getConta());
		contaProcess.setSaldo(item.getSaldo());
		contaProcess.setStatus(item.getStatus());
		
		 ReceitaService receitaService = new ReceitaService();
		 
	     boolean resultado = receitaService.atualizarConta(completeToLeft(item.getAgencia()), item.getConta().replaceAll("-", ""), item.getSaldo(), item.getStatus());
	     
	     if(resultado) {
	    	 contaProcess.setResultado("Atualizado");	 
	     } else {
	    	 contaProcess.setResultado("Falha");	 
	     }
		return contaProcess;
	}
	
	private String completeToLeft(String value) {
		return String.format("%04d", Integer.parseInt(value));
	}

}
