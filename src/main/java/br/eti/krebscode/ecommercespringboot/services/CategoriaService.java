package br.eti.krebscode.ecommercespringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		
		// Optionar é usado em vez de usar apenas Categoria pois isso evita 
		// erro de nullPointException
		Optional<Categoria> objCategoria = categoriaRepository.findById(id);
		
		// se não existir o objeto, retorna null
		return objCategoria.orElse(null);
		
	}

}
