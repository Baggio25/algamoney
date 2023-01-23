package com.algaworks.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.dto.EnderecoDTO;
import com.algaworks.algamoneyapi.model.Cidade;
import com.algaworks.algamoneyapi.model.Endereco;
import com.algaworks.algamoneyapi.repository.CidadeRepository;
import com.algaworks.algamoneyapi.repository.EnderecoRepository;
import com.algaworks.algamoneyapi.service.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public EnderecoDTO buscarEnderecoPorId(Long codigo) {
		Endereco endereco = buscarEntidadeEndereco(codigo);
		return new EnderecoDTO(endereco);
	}
	
	public Page<EnderecoDTO> listarEnderecos(Pageable pageable) {
		Page<Endereco> enderecos = enderecoRepository.findAll(pageable);		
		return enderecos.map(endereco -> new EnderecoDTO(endereco));
	}
	
	public EnderecoDTO salvar(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		endereco = persist(enderecoDTO, endereco);
		
		return new EnderecoDTO(endereco);
	}
	
	public EnderecoDTO atualizar(Long codigo, EnderecoDTO enderecoDTO) {
		Endereco endereco = buscarEntidadeEndereco(codigo);
		endereco = persist(enderecoDTO, endereco);
		
		return new EnderecoDTO(endereco);
	}

	private Endereco persist(EnderecoDTO enderecoDTO, Endereco endereco) {
		dtoToEntity(enderecoDTO, endereco);		
		endereco = enderecoRepository.save(endereco);
		
		return endereco;
	}
	
	private Endereco buscarEntidadeEndereco(Long codigo) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findById(codigo);
		Endereco endereco = enderecoOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return endereco;
	}

	private void dtoToEntity(EnderecoDTO enderecoDTO, Endereco endereco) {
		endereco.setLogradouro(enderecoDTO.getLogradouro());	
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCep(enderecoDTO.getCep());
		
		Cidade cidade = cidadeRepository.getReferenceById(enderecoDTO.getCidadeId()); 
		endereco.setCidade(cidade);
	}

	public void excluir(Long codigo) {
		enderecoRepository.deleteById(codigo);		
	}
	
}
