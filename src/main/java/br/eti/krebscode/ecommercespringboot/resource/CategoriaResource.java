package br.eti.krebscode.ecommercespringboot.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.dto.CategoriaDTO;
import br.eti.krebscode.ecommercespringboot.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaServices; 
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj = categoriaServices.find(id);
		
		return ResponseEntity.ok().body(obj);
				
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		// converte dto para objeto
		Categoria obj = categoriaServices.fromDto(objDto);
		
		obj = categoriaServices.insert(obj);
		
		// url que vai ser retornada para o cliente com o /id
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = categoriaServices.fromDto(objDto);
		
		// seta o id para poder fazer o update
		obj.setId(id);
		categoriaServices.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaServices.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> list = categoriaServices.findAll();
		
		// transforma lista Categoria em uma lista CategoriaDTO
		List<CategoriaDTO> listDTO = list.stream()
				.map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); 
		
		return ResponseEntity.ok().body(listDTO);
				
	}
	
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPagination(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		
		direction = direction.toUpperCase();
		
		Page<Categoria> list = categoriaServices.findPagination(page, linesPerPage, orderBy, direction);
		
		// transforma lista Categoria em uma lista CategoriaDTO
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj)); 
		
		return ResponseEntity.ok().body(listDTO);
				
	}
	
}
