package com.algaworks.algamoneyapi.exceptions.dto;

public class ErroDTO {
	
	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
	
	public ErroDTO(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}
	
	
}
