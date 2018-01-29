import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lazybakers.controller.PizzaToppingController;
import lazybakers.model.entity.PizzaTopping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:config/spring/appContext-interceptor.xml",
        "classpath:config/spring/appContext-repository.xml",
        "classpath:config/spring/appContext-jdbc-test.xml",
        "classpath:config/spring/appContext-service.xml",
        "classpath:config/spring/appContext-servlet.xml",
        "classpath:config/spring/appContext-ywp2.xml"
})
@WebAppConfiguration
public class PizzaToppingControllerTest {
	
	@Autowired
	PizzaToppingController pizzaToppingController;
	

	@Test
	public void test() {
		List<PizzaTopping> pizzaTopping = pizzaToppingController.getPizzaToppingByPizza(1);
		assertNotNull(pizzaTopping);
	}

}
