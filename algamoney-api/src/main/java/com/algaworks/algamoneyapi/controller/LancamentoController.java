package com.algaworks.algamoneyapi.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.dto.LancamentoDTO;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.service.LancamentoService;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<Page<LancamentoDTO>> listarLancamentos(Pageable pageable) {
		Page<LancamentoDTO> lista = lancamentoService.listarLancamentos(pageable);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<LancamentoDTO> buscarLancamentoPorCodigo(@PathVariable Long codigo) {
		LancamentoDTO lancamento = lancamentoService.buscarLancamentoPorCodigo(codigo);
		return ResponseEntity.ok(lancamento);
	}
	
	@PostMapping
	public ResponseEntity<LancamentoDTO> salvar(@Valid @RequestBody LancamentoDTO lancamentoDTO,
				HttpServletResponse response) {
		lancamentoDTO = lancamentoService.salvar(lancamentoDTO);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoDTO.getCodigo()));		
	
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoDTO);
	}
}
