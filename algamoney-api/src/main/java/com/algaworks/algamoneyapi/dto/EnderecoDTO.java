package com.algaworks.algamoneyapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algamoneyapi.model.Endereco;


public class EnderecoDTO {

	private Long codigo;
	
	@NotBlank(message = "Logradouro é obrigatório")
	@Size(message= "Logradouro deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String logradouro;

	@NotBlank(message = "Complemento é obrigatório")
	@Size(message= "Complemento deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String complemento;
	
	@NotBlank(message = "Bairro é obrigatório")
	@Size(message= "Bairro deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String bairro;
	
	@NotBlank(message = "CEP é obrigatório")
	@Size(message= "CEP deve conter entre 3 e 50 caracteres", min = 3, max = 50)
	private String cep;

	@NotNull(message = "Cidade é obrigatória")
	private Long cidadeId;
	
	public EnderecoDTO() {
	}
	
	public EnderecoDTO(Endereco endereco) {
		codigo = endereco.getCodigo();
		logradouro = endereco.getLogradouro();
		complemento = endereco.getComplemento();
		bairro = endereco.getBairro();
		cep = endereco.getCep();
		cidadeId = endereco.getCidade().getCodigo();
	}
		
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

}
