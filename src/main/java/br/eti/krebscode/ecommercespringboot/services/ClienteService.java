package br.eti.krebscode.ecommercespringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.eti.krebscode.ecommercespringboot.domain.Cliente;
import br.eti.krebscode.ecommercespringboot.dto.ClienteDTO;
import br.eti.krebscode.ecommercespringboot.repositories.ClienteRepository;
import br.eti.krebscode.ecommercespringboot.services.exceptions.DataIntegrivityException;
import br.eti.krebscode.ecommercespringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		
		// Optionar é usado em vez de usar apenas Cliente pois isso evita 
		// erro de nullPointException
		Optional<Cliente> objCliente = clienteRepository.findById(id);
		
		// se não existir o objeto, retorna null
		return objCliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); // aproveita o find por causa do tratamento
		
		updateData(newObj, obj); 
		
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
				
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			 throw new DataIntegrivityException("Não é possivel excluir cliente com dados relacionados");
		}
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPagination(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}
	
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
