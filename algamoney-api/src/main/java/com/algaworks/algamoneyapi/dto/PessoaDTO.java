package com.algaworks.algamoneyapi.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.algaworks.algamoneyapi.model.Pessoa;

public class PessoaDTO {

	private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
	@Size(message= "Nome deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String nome;
	
	private Boolean ativo;
	
	private Long enderecoId;

	public PessoaDTO() {
	}
	
	public PessoaDTO(Pessoa pessoa) {
		codigo = pessoa.getCodigo();
		nome = pessoa.getNome();
		ativo = pessoa.getAtivo();
		enderecoId = pessoa.getEndereco().getCodigo();
	}
	
	public PessoaDTO(Long codigo, String nome, Boolean ativo, Long enderecoId) {
		this.codigo = codigo;
		this.nome = nome;
		this.ativo = ativo;
		this.enderecoId = enderecoId;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
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
		PessoaDTO other = (PessoaDTO) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
