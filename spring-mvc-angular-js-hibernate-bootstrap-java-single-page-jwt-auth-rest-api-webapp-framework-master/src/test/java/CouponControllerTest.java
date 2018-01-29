import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lazybakers.controller.CouponController;
import lazybakers.model.entity.Coupon;

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
public class CouponControllerTest {
	
	@Autowired
	CouponController couponController;
	

	@Test
	public void test() {
		Coupon coupon = couponController.getCoupon("test");
		assertNotNull(coupon);
	}

}
