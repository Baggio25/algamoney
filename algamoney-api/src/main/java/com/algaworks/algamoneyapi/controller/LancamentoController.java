package com.algaworks.algamoneyapi.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.dto.LancamentoDTO;
import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.exceptions.dto.ErroDTO;
import com.algaworks.algamoneyapi.service.LancamentoService;
import com.algaworks.algamoneyapi.service.exceptions.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

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

	@ExceptionHandler(PessoaInexistenteOuInativaException.class)
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(
			PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<ErroDTO> erros = Arrays.asList(new ErroDTO(mensagemUsuario, mensagemDesenvolvedor));

		return ResponseEntity.badRequest().body(erros);
	}

}
