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

import com.algaworks.algamoneyapi.dto.PessoaDTO;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<Page<PessoaDTO>> listar(Pageable pageable) {
		Page<PessoaDTO> lista = pessoaService.listarPessoas(pageable);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<PessoaDTO> buscarPessoaPorId(@PathVariable Long codigo) {
		PessoaDTO pessoaDTO = pessoaService.buscarPessoaPorId(codigo);
		return ResponseEntity.ok(pessoaDTO);
	}
		
	@PostMapping
	public ResponseEntity<PessoaDTO> salvar(@Valid @RequestBody PessoaDTO pessoaDTO, HttpServletResponse response){
		pessoaDTO = pessoaService.salvar(pessoaDTO);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaDTO.getCodigo()));		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDTO);
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody PessoaDTO pessoaDTO) {
		pessoaDTO = pessoaService.atualizar(codigo, pessoaDTO);
		return ResponseEntity.ok(pessoaDTO);
	}
	
	@PutMapping(value = "/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizaAtivo(codigo, ativo);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		pessoaService.excluir(codigo);
	}
}
