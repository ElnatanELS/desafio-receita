package com.southsystem.desafioreceita.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.southsystem.desafioreceita.entidades.Conta;
/**
 * Classe para mapear a entidade de entrada
 * @author Elnatan Emanuel
 *
 */
public class ContaFieldSetMapper implements FieldSetMapper<Conta> {

	@Override
	public Conta mapFieldSet(FieldSet fieldSet) throws BindException {
		
		return Conta.builder()
			.agencia(fieldSet.readString("agencia"))
			.conta(fieldSet.readString("conta"))
			.saldo(Double.parseDouble(fieldSet.readString("saldo").replaceAll(",",".")))
			.status(fieldSet.readString("status"))
			.build();
	}

}
