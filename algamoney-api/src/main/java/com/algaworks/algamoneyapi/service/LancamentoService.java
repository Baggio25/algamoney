package com.algaworks.algamoneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.dto.LancamentoDTO;
import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.model.enums.TipoLancamento;
import com.algaworks.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoneyapi.service.exceptions.ResourceNotFoundException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Page<LancamentoDTO> listarLancamentos(Pageable pageable) {
		Page<Lancamento> lancamentos = lancamentoRepository.findAll(pageable);
		return lancamentos.map(lancamento -> new LancamentoDTO(lancamento));
	}
	
	public LancamentoDTO buscarLancamentoPorCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new LancamentoDTO(lancamento);
	}
	
	public LancamentoDTO salvar(LancamentoDTO lancamentoDTO) {
		Lancamento lancamento = new Lancamento();
		lancamento = persist(lancamentoDTO, lancamento);
		
		return new LancamentoDTO(lancamento);
	}
	
	private Lancamento persist(LancamentoDTO lancamentoDTO, 
			Lancamento lancamento) {
		dtoToEntity(lancamentoDTO, lancamento);
		return lancamentoRepository.save(lancamento);
	}
	
	private void dtoToEntity(LancamentoDTO lancamentoDTO, Lancamento lancamento) {
		lancamento.setDescricao(lancamentoDTO.getDescricao());
		lancamento.setDataVencimento(lancamentoDTO.getDataVencimento());
		
		if(lancamentoDTO.getDataPagamento() != null) {
			lancamento.setDataPagamento(lancamentoDTO.getDataPagamento());
		}
		
		lancamento.setValor(lancamentoDTO.getValor());
		lancamento.setTipo(TipoLancamento.valueOf(lancamentoDTO.getTipo()));
		lancamento.setCategoria(lancamentoDTO.getCategoria());
		lancamento.setPessoa(lancamentoDTO.getPessoa());
	}
}
