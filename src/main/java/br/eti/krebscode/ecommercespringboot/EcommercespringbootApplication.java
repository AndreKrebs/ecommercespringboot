package br.eti.krebscode.ecommercespringboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.domain.Cidade;
import br.eti.krebscode.ecommercespringboot.domain.Cliente;
import br.eti.krebscode.ecommercespringboot.domain.Endereco;
import br.eti.krebscode.ecommercespringboot.domain.Estado;
import br.eti.krebscode.ecommercespringboot.domain.ItemPedido;
import br.eti.krebscode.ecommercespringboot.domain.Pagamento;
import br.eti.krebscode.ecommercespringboot.domain.PagamentoComBoleto;
import br.eti.krebscode.ecommercespringboot.domain.PagamentoComCartao;
import br.eti.krebscode.ecommercespringboot.domain.Pedido;
import br.eti.krebscode.ecommercespringboot.domain.Produto;
import br.eti.krebscode.ecommercespringboot.domain.enums.EstadoPagamento;
import br.eti.krebscode.ecommercespringboot.domain.enums.TipoCliente;
import br.eti.krebscode.ecommercespringboot.repositories.CategoriaRepository;
import br.eti.krebscode.ecommercespringboot.repositories.CidadeRepository;
import br.eti.krebscode.ecommercespringboot.repositories.ClienteRepository;
import br.eti.krebscode.ecommercespringboot.repositories.EnderecoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.EstadoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.ItemPedidoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.PagamentoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.PedidoRepository;
import br.eti.krebscode.ecommercespringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class EcommercespringbootApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EcommercespringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônicos");
		Categoria cat4 = new Categoria(null, "Jogos pc");
		Categoria cat5 = new Categoria(null, "Drones");
		Categoria cat6 = new Categoria(null, "Celulares");
		Categoria cat7 = new Categoria(null, "Refrigeração");
		Categoria cat8 = new Categoria(null, "Componentes");
		Categoria cat9 = new Categoria(null, "Redes");
		Categoria cat10 = new Categoria(null, "Arduinos");
		Categoria cat11 = new Categoria(null, "Raspberry Pi");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "254154788", TipoCliente.PESSOAFISICA, passwordEncoder.encode("1234"));
		cli1.getTelefones().addAll(Arrays.asList("3524-5878", "3335-7500"));
		
//		TODO: Adicionar cliente com perfil de ADMIN
		
		Endereco end1 = new Endereco(null, "Rua Flores", "1234", "casa 1", "Jardim", "88880-444", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "756", "apto 32", "Centro", "88880-444", cli1, cid2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
						 
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
						
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		ItemPedido itemPed1 = new ItemPedido(ped1, p1, 1, 2000.00, 0D);
		ItemPedido itemPed2 = new ItemPedido(ped1, p3, 2, 80.00, 0D);
		ItemPedido itemPed3 = new ItemPedido(ped2, p2, 1, 800.00, 100D);
		
		ped1.getItens().addAll(Arrays.asList(itemPed1, itemPed3));
		ped1.getItens().addAll(Arrays.asList(itemPed2));
		
		p1.getItens().add(itemPed1);
		p2.getItens().add(itemPed2);
		p3.getItens().add(itemPed3);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		clienteRepository.save(cli1);
		
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPed1, itemPed2, itemPed3));
	}
}
