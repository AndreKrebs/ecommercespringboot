package br.eti.krebscode.ecommercespringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.krebscode.ecommercespringboot.domain.Cliente;
import br.eti.krebscode.ecommercespringboot.repositories.ClienteRepository;
import br.eti.krebscode.ecommercespringboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		
		// Optionar é usado em vez de usar apenas Cliente pois isso evita 
		// erro de nullPointException
		Optional<Cliente> objCliente = clienteRepository.findById(id);
		
		// se não existir o objeto, retorna null
		return objCliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}

}
