

import static org.junit.Assert.*;

import org.junit.Test;

import lazybakers.model.repository.ToppingRepository;
import lazybakers.model.repository.impl.ToppingRepositoryImpl;

public class ToppingRepositoryTest {

	ToppingRepository toppingRepository = new ToppingRepositoryImpl();

	@Test
	public void test() {
		int createTopping = toppingRepository.createTopping("Test", 34, 100, true);
		assertNull(createTopping);
	}

}