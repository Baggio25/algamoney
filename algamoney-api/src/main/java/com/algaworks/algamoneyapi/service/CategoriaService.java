package com.algaworks.algamoneyapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.dto.CategoriaDTO;
import com.algaworks.algamoneyapi.model.Categoria;
import com.algaworks.algamoneyapi.repository.CategoriaRepository;
import com.algaworks.algamoneyapi.service.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaDTO buscarCategoriaPorId(Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		Categoria categoria = categoriaOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new CategoriaDTO(categoria);
	}
	
	public List<CategoriaDTO> listarCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();		
		return categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
	}
	
	public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		dtoToEntity(categoriaDTO, categoria);
		
		categoria = categoriaRepository.save(categoria);
		
		return new CategoriaDTO(categoria);
	}

	public void delete(Long id) {
		categoriaRepository.deleteById(id);		
	}
	
	private void dtoToEntity(CategoriaDTO categoriaDTO, Categoria categoria) {
		categoria.setNome(categoriaDTO.getNome());		
	}

	
	
}
