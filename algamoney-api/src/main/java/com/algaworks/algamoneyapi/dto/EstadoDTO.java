package com.algaworks.algamoneyapi.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EstadoDTO {

	private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
	@Size(message= "Nome deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String nome;
	
	@NotBlank(message = "Sigla é obrigatória")
	@Size(message="Sigla deve conter entre 3 e 50 caracteres", min = 2, max = 2)
	private String sigla;

	public EstadoDTO() {
	}

	public EstadoDTO(Long codigo, String nome, String sigla) {
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
		EstadoDTO other = (EstadoDTO) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
