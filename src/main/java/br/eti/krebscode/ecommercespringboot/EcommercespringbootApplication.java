package br.eti.krebscode.ecommercespringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.repositories.CategoriaRepository;

@SpringBootApplication
public class EcommercespringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommercespringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}
}
