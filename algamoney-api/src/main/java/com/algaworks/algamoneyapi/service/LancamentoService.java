package com.algaworks.algamoneyapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.dto.LancamentoDTO;
import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.model.enums.TipoLancamento;
import com.algaworks.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;
import com.algaworks.algamoneyapi.service.exceptions.PessoaInexistenteOuInativaException;
import com.algaworks.algamoneyapi.service.exceptions.ResourceNotFoundException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<LancamentoDTO> listarLancamentos(Pageable pageable, LancamentoFilter lancamentoFilter) {
		List<Lancamento> lancamentos = lancamentoRepository.filtrar(pageable, lancamentoFilter)
				.stream().collect(Collectors.toList());
		
		return lancamentos.stream().map(lancamento -> new LancamentoDTO(lancamento))
				.collect(Collectors.toList());
	}
	
	public LancamentoDTO buscarLancamentoPorCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new LancamentoDTO(lancamento);
	}
	
	public LancamentoDTO salvar(LancamentoDTO lancamentoDTO) throws PessoaInexistenteOuInativaException {
		Lancamento lancamento = new Lancamento();
		lancamento = persist(lancamentoDTO, lancamento);
		
		return new LancamentoDTO(lancamento);
	}
	
	private Lancamento persist(LancamentoDTO lancamentoDTO, 
			Lancamento lancamento) {		
		validaPessoa(lancamentoDTO);	
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

	private void validaPessoa(LancamentoDTO lancamentoDTO) {
		Pessoa pessoa = pessoaRepository.getReferenceById(lancamentoDTO.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}
}
