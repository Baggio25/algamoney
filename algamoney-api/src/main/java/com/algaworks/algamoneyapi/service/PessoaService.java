package com.algaworks.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.dto.PessoaDTO;
import com.algaworks.algamoneyapi.model.Endereco;
import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.EnderecoRepository;
import com.algaworks.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoneyapi.service.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public PessoaDTO buscarPessoaPorId(Long codigo) {
		Pessoa pessoa = buscarEntidadePessoa(codigo);		
		return new PessoaDTO(pessoa);
	}

	public Page<PessoaDTO> listarPessoas(Pageable pageable) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);		
		return pessoas.map(pessoa -> new PessoaDTO(pessoa));
	}
	
	public PessoaDTO salvar(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa = persist(pessoaDTO, pessoa);
		
		return new PessoaDTO(pessoa);
	}

	public PessoaDTO atualizar(Long codigo, PessoaDTO pessoaDTO) {
		Pessoa pessoa = buscarEntidadePessoa(codigo);
		pessoa = persist(pessoaDTO, pessoa);
		
		return new PessoaDTO(pessoa);
	}

	public void excluir(Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	public void atualizaAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoa = buscarEntidadePessoa(codigo);
		pessoa.setAtivo(ativo);
		pessoaRepository.save(pessoa);
	}
	
	private Pessoa persist(PessoaDTO pessoaDTO, Pessoa pessoa) {
		dtoToEntity(pessoaDTO, pessoa);		
		pessoa = pessoaRepository.save(pessoa);
		
		return pessoa;
	}
		
	private Pessoa buscarEntidadePessoa(Long codigo) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);
		Pessoa pessoa = pessoaOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return pessoa;
	}	
	
	private void dtoToEntity(PessoaDTO pessoaDTO, Pessoa pessoa) {
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setAtivo(pessoaDTO.getAtivo());
		
		Endereco endereco = enderecoRepository.getReferenceById(pessoaDTO.getEnderecoId());		
		pessoa.setEndereco(endereco);
	}
	
}
