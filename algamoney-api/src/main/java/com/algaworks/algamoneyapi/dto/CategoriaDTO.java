package com.algaworks.algamoneyapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.algaworks.algamoneyapi.model.Categoria;

public class CategoriaDTO {

	private Long codigo;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(message= "Nome deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String nome;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria categoria) {
		codigo = categoria.getCodigo();
		nome = categoria.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
}
