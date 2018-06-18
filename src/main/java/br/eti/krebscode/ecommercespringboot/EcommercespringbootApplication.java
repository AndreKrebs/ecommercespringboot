package br.eti.krebscode.ecommercespringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.domain.Cidade;
import br.eti.krebscode.ecommercespringboot.domain.Cliente;
import br.eti.krebscode.ecommercespringboot.domain.Endereco;
import br.eti.krebscode.ecommercespringboot.domain.Estado;
import br.eti.krebscode.ecommercespringboot.domain.Produto;
import br.eti.krebscode.ecommercespringboot.domain.enums.TipoCliente;
import br.eti.krebscode.ecommercespringboot.repositories.CategoriaRepository;
import br.eti.krebscode.ecommercespringboot.repositories.CidadeRepository;
import br.eti.krebscode.ecommercespringboot.repositories.ClienteRepository;
import br.eti.krebscode.ecommercespringboot.repositories.EnderecoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.EstadoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class EcommercespringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EcommercespringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "254154788", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3524-5878", "3335-7500"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "1234", "casa 1", "Jardim", "88880-444", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "756", "apto 32", "Centro", "88880-444", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
						
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.save(cli1);
		
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}
}
