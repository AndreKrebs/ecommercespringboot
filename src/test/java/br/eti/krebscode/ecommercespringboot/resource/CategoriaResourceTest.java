package br.eti.krebscode.ecommercespringboot.resource;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.eti.krebscode.ecommercespringboot.EcommercespringbootApplication;
import br.eti.krebscode.ecommercespringboot.domain.Categoria;

/**
 * 
 * @author andre
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommercespringbootApplication.class)
@ActiveProfiles("test") // para carregar as configuracoes do arquivo application-test.properties
public class CategoriaResourceTest {
	
	@Autowired
	private CategoriaResource categoriaResource;
	
	private MockMvc restCategoriaMockMvc;
	
	private Categoria categoria;
	
	@Before
    public void setup() {
		this.restCategoriaMockMvc = MockMvcBuilders.standaloneSetup(categoriaResource).build();
	}
	
	@Test
	public void getAllCategorias() throws Exception {
		// TODO: apenas para o teste passar, depois esse valor deve ser alterado por um objeto com dados do banco de teste
		int testId = 1;
		
		this.restCategoriaMockMvc.perform(MockMvcRequestBuilders.get("/categorias"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(
				MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").value(Matchers.hasItem(testId)));
	}
	
}
