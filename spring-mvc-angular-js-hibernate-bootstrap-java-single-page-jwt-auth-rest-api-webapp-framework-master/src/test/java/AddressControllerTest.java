import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lazybakers.controller.AddressController;
import lazybakers.model.entity.Address;

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
public class AddressControllerTest {
	
	@Autowired
	AddressController addressController;
	

	@Test
	public void testById() {
		Address address = addressController.getAddress(1);
		assertNotNull(address);
	}
	
	@Test
	public void testByUid() {
		List<Address> list = addressController.getAddressByUser(1);
		assertNotNull(list);
	}
	

	

}
