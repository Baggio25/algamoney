package com.algaworks.algamoneyapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.dto.CategoriaDTO;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		List<CategoriaDTO> lista = categoriaService.listarCategorias();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable Long codigo) {
		CategoriaDTO categoriaDTO = categoriaService.buscarCategoriaPorId(codigo);
		return ResponseEntity.ok(categoriaDTO);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> salvar(@Valid @RequestBody CategoriaDTO categoriaDTO, HttpServletResponse response){
		categoriaDTO = categoriaService.salvar(categoriaDTO);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaDTO.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDTO);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		categoriaService.delete(codigo);
	}
}
