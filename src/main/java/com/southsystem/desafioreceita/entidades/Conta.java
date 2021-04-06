package com.southsystem.desafioreceita.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {
	private String agencia;
	private String conta;
	private Double saldo;
	private String status;
	private String resultado;
}
