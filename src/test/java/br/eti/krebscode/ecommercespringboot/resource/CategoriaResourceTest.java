package br.eti.krebscode.ecommercespringboot.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.eti.krebscode.ecommercespringboot.EcommercespringbootApplication;
import br.eti.krebscode.ecommercespringboot.domain.Categoria;
import br.eti.krebscode.ecommercespringboot.dto.CategoriaDTO;
import br.eti.krebscode.ecommercespringboot.repositories.CategoriaRepository;
import br.eti.krebscode.ecommercespringboot.utils.TestUtil;

/**
 * 
 * @author andre
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommercespringbootApplication.class)
@ActiveProfiles("test") // para carregar as configuracoes do arquivo application-test.properties
public class CategoriaResourceTest {
	
	private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";
	
	@Autowired
	private CategoriaResource categoriaResource;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EntityManager em;
	
	private MockMvc restCategoriaMockMvc;
	
	private Categoria categoria;
	
	@Before
    public void setup() {
		this.restCategoriaMockMvc = MockMvcBuilders.standaloneSetup(categoriaResource).build();
	}
	
	public static Categoria createEntity(EntityManager em) {
		// cria uma entidade para o teste
        Categoria categoria = new Categoria();
        
        categoria.setNome(DEFAULT_NOME);
                
        return categoria;
    }
	
	@Before
    public void initTest() {
		// deleteAll nos repositorios referentes as entidades usadas no teste
		categoriaRepository.deleteAll();
        categoria = createEntity(em);
    }
	
	@Test
	public void createCategoria() throws Exception{
		int databaseSizeBeforeCreate = categoriaRepository.findAll().size();
		
		// cria o DTO da categoria
		CategoriaDTO categoriaDto = new CategoriaDTO(categoria);
		
		// faz o post do DTO
		restCategoriaMockMvc.perform(post("/categorias")
		.contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(categoriaDto)))
        .andExpect(status().isCreated());
		
		// valida categoria da base de dados
		List<Categoria> listaCategoria = categoriaRepository.findAll();
		assertThat(listaCategoria).hasSize(databaseSizeBeforeCreate + 1);
		Categoria testCompany = listaCategoria.get(listaCategoria.size() - 1);
		assertThat(testCompany.getNome()).isEqualTo(DEFAULT_NOME);
	}
	
	@Test
	public void getAllCategorias() throws Exception {
		categoriaRepository.saveAndFlush(categoria);
		
		// testa retorno da consulta
		restCategoriaMockMvc.perform(get("/categorias"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(
				MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.[*].id").value(hasItem(categoria.getId().intValue())))
		.andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
	}
	
}
