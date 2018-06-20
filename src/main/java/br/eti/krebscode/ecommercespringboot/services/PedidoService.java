package br.eti.krebscode.ecommercespringboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.krebscode.ecommercespringboot.domain.Pedido;
import br.eti.krebscode.ecommercespringboot.repositories.PedidoRepository;
import br.eti.krebscode.ecommercespringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		
		// Optionar é usado em vez de usar apenas Pedido pois isso evita 
		// erro de nullPointException
		Optional<Pedido> objPedido = pedidoRepository.findById(id);
		
		// se não existir o objeto, retorna null
		return objPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}

}
