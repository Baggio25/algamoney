package com.algaworks.algamoneyapi.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.dto.EnderecoDTO;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<Page<EnderecoDTO>> listar(Pageable pageable) {
		Page<EnderecoDTO> lista = enderecoService.listarEnderecos(pageable);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable Long codigo) {
		EnderecoDTO enderecoDTO = enderecoService.buscarEnderecoPorId(codigo);
		return ResponseEntity.ok(enderecoDTO);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDTO> salvar(@Valid @RequestBody EnderecoDTO enderecoDTO, HttpServletResponse response){
		enderecoDTO = enderecoService.salvar(enderecoDTO);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoDTO.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDTO);
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody EnderecoDTO enderecoDTO) {
		enderecoDTO = enderecoService.atualizar(codigo, enderecoDTO);
		return ResponseEntity.ok(enderecoDTO);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		enderecoService.excluir(codigo);
	}
}
