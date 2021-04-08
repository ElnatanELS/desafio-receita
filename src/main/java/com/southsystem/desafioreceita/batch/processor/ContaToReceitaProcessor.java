package com.southsystem.desafioreceita.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.southsystem.desafioreceita.entidades.Conta;
import com.southsystem.desafioreceita.services.ReceitaService;

/**
 * Classe que processa a linha do arquivo
 * @author Elnatan Emanuel
 *
 */

@Component
public class ContaToReceitaProcessor implements ItemProcessor<Conta, Conta> {
	
	ReceitaService receitaService = new ReceitaService();

	@Override
	public Conta process(Conta item) throws Exception {
		Conta contaProcess = new Conta();

		contaProcess.setAgencia(item.getAgencia());
		contaProcess.setConta(item.getConta());
		contaProcess.setSaldo(item.getSaldo());
		contaProcess.setStatus(item.getStatus());


		boolean resultado = receitaService.atualizarConta(completeToLeft(item.getAgencia()),
				item.getConta().replaceAll("-", ""), item.getSaldo(), item.getStatus());

		if (resultado) {
			contaProcess.setResultado("Atualizado");
		} else {
			contaProcess.setResultado("Falha");
		}
		return contaProcess;
	}
	
	/**
	 * Metodo que preenche com zero a esquerda 4 casas 
	 * @param value
	 * @return
	 */

	private String completeToLeft(String value) {
		return String.format("%04d", Integer.parseInt(value));
	}

}
