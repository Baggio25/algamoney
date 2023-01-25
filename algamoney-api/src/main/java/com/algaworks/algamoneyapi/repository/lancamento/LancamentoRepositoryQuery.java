package com.algaworks.algamoneyapi.repository.lancamento;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(Pageable pageable, LancamentoFilter lancamentoFilter);
	
}
