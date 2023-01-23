package com.algaworks.algamoneyapi.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algamoneyapi.model.Cidade;

public class CidadeDTO {

	private Long codigo;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(message= "Nome deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String nome;
	
	@NotNull(message = "Estado é obrigatório")
	private Long estadoId;
	
	public CidadeDTO() {

	}

	public CidadeDTO(Cidade cidade) {
		codigo = cidade.getCodigo();
		nome = cidade.getNome();
		estadoId = cidade.getEstado().getCodigo();
	}
	
	public CidadeDTO(Long codigo, String nome, Long estadoId) {
		this.codigo = codigo;
		this.nome = nome;
		this.estadoId = estadoId;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CidadeDTO other = (CidadeDTO) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
