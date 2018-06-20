package br.eti.krebscode.ecommercespringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.repositories.CategoriaRepository;
import br.eti.krebscode.ecommercespringboot.services.exceptions.DataIntegrivityException;
import br.eti.krebscode.ecommercespringboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		
		// Optionar é usado em vez de usar apenas Categoria pois isso evita 
		// erro de nullPointException
		Optional<Categoria> objCategoria = categoriaRepository.findById(id);
		
		// se não existir o objeto, retorna null
		return objCategoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
		
	}

	public Categoria insert(Categoria obj) {
		if(obj.getId() == null) {
			return categoriaRepository.save(obj);
		}
		return null;
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); // aproveita o find por causa do tratamento
		
		return categoriaRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
				
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			 throw new DataIntegrivityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
}
