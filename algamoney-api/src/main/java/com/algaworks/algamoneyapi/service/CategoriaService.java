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
		Categoria categoria = buscarEntidadeCategoria(id);		
		return new CategoriaDTO(categoria);
	}
	
	public List<CategoriaDTO> listarCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();		
		return categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
	}
	
	public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria = persist(categoriaDTO, categoria);
		
		return new CategoriaDTO(categoria);
	}

	public CategoriaDTO atualizar(Long codigo, CategoriaDTO categoriaDTO) {
		Categoria categoria = buscarEntidadeCategoria(codigo);
		categoria = persist(categoriaDTO, categoria);
		
		return new CategoriaDTO(categoria);
	}

	public void delete(Long id) {
		categoriaRepository.deleteById(id);		
	}

	private Categoria persist(CategoriaDTO categoriaDTO, Categoria categoria) {
		dtoToEntity(categoriaDTO, categoria);		
		categoria = categoriaRepository.save(categoria);
		
		return categoria;
	}
	
	private Categoria buscarEntidadeCategoria(Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		Categoria categoria = categoriaOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return categoria;
	}
	
	private void dtoToEntity(CategoriaDTO categoriaDTO, Categoria categoria) {
		categoria.setNome(categoriaDTO.getNome());		
	}
	
}
