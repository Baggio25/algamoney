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
	
	public PessoaDTO buscarPessoaPorId(Long id) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
		Pessoa pessoa = pessoaOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new PessoaDTO(pessoa);
	}
	
	public Page<PessoaDTO> listarPessoas(Pageable pageable) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);		
		return pessoas.map(pessoa -> new PessoaDTO(pessoa));
	}
	
	public PessoaDTO salvar(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		dtoToEntity(pessoaDTO, pessoa);
		
		pessoa = pessoaRepository.save(pessoa);
		
		return new PessoaDTO(pessoa);
	}

	private void dtoToEntity(PessoaDTO pessoaDTO, Pessoa pessoa) {
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setAtivo(pessoaDTO.getAtivo());
		
		Endereco endereco = enderecoRepository.getReferenceById(pessoaDTO.getEnderecoId());		
		pessoa.setEndereco(endereco);
	}
	
}
