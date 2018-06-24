package br.eti.krebscode.ecommercespringboot.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.eti.krebscode.ecommercespringboot.EcommercespringbootApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommercespringbootApplication.class)
@ActiveProfiles("test") 
public class CategoriaServiceTest {

	@Test
	public void contextLoads() {
	
	}
}