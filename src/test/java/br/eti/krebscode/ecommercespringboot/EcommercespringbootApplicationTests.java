package br.eti.krebscode.ecommercespringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommercespringbootApplication.class)
@ActiveProfiles("test") 
public class EcommercespringbootApplicationTests {

	@Test
	public void contextLoads() {
	}

}
