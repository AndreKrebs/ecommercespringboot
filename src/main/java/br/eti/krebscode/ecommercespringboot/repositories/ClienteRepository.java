package br.eti.krebscode.ecommercespringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.eti.krebscode.ecommercespringboot.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
}
