package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Topping;
import lazybakers.model.repository.ToppingRepository;
import lazybakers.service.ToppingService;

@Service("toppingService")
@Transactional
public class ToppingServiceImpl implements ToppingService {
	
	@Autowired
	ToppingRepository toppingRepository;

	@Override
	public Integer createTopping(String toppingname, float price, int stock, boolean vegetarian) {
		return toppingRepository.createTopping(toppingname, price, stock, vegetarian);
	}

	@Override
	public void updateTopping(int toppindId, String toppingname, float price, int stock, boolean vegetarian) {
		toppingRepository.updateTopping(toppindId, toppingname, price, stock, vegetarian);
		
	}

	@Override
	public void deleteTopping(int toppingId) {
		toppingRepository.deleteTopping(toppingId);
		
	}

	@Override
	public Topping getToppingById(int toppingId) {
		return toppingRepository.getToppingById(toppingId);
	}

	@Override
	public List<Topping> getAllTopping() {
		return toppingRepository.getAllTopping();
	}
	
	
	
}
