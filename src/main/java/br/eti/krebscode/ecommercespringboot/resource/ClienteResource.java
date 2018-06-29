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

import br.eti.krebscode.ecommercespringboot.domain.Cliente;
import br.eti.krebscode.ecommercespringboot.dto.ClienteDTO;
import br.eti.krebscode.ecommercespringboot.dto.ClienteNewDTO;
import br.eti.krebscode.ecommercespringboot.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteServices; 
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = clienteServices.find(id);
		
		return ResponseEntity.ok().body(obj);
				
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		// converte dto para objeto
		Cliente obj = clienteServices.fromDto(objDto);
		
		obj = clienteServices.insert(obj);
		
		// url que vai ser retornada para o cliente com o /id
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
	

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = clienteServices.fromDto(objDto);
		
		// seta o id para poder fazer o update
		obj.setId(id);
		obj = clienteServices.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteServices.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> list = clienteServices.findAll();
		
		// transforma lista Cliente em uma lista ClienteDTO
		List<ClienteDTO> listDTO = list.stream()
				.map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); 
		
		return ResponseEntity.ok().body(listDTO);
				
	}
	
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPagination(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		
		direction = direction.toUpperCase();
		
		Page<Cliente> list = clienteServices.findPagination(page, linesPerPage, orderBy, direction);
		
		// transforma lista Cliente em uma lista ClienteDTO
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj)); 
		
		return ResponseEntity.ok().body(listDTO);
				
	}
	
}
