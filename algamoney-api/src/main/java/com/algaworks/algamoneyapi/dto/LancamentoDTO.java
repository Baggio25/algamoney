package com.algaworks.algamoneyapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algamoneyapi.model.Categoria;
import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.model.Pessoa;

public class LancamentoDTO {

	private Long codigo;
	
	@NotBlank(message = "Descrição é obrigatória")
	@Size(message= "Descrição deve conter entre 10 e 200 caracteres", min = 10, max = 200)
	private String descricao;
	
	@NotNull(message = "Data de Vencimento é obrigatória")
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	@NotNull(message = "Valor é obrigatório")
	private BigDecimal valor;
	
	private String observacao;
	
	@NotNull(message = "Tipo é obrigatório")
	private String tipo;
	
	@NotNull(message = "Categoria é obrigatória")
	private Categoria categoria;
	
	@NotNull(message = "Pessoa é obrigatória")
	private Pessoa pessoa;

	public LancamentoDTO() {
		// TODO Auto-generated constructor stub
	}

	public LancamentoDTO(Long codigo, String descricao, LocalDate dataVencimento, LocalDate dataPagamento,
			BigDecimal valor, String observacao, String tipo, Categoria categoria, Pessoa pessoa) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.observacao = observacao;
		this.tipo = tipo;
		this.categoria = categoria;
		this.pessoa = pessoa;
	}
	
	public LancamentoDTO(Lancamento lancamento) {
		codigo = lancamento.getCodigo();
		descricao = lancamento.getDescricao();
		dataVencimento = lancamento.getDataVencimento();
		dataPagamento = lancamento.getDataPagamento();
		valor = lancamento.getValor();
		observacao = lancamento.getObservacao();
		tipo = lancamento.getTipo().toString();
		categoria = lancamento.getCategoria();
		pessoa = lancamento.getPessoa();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
