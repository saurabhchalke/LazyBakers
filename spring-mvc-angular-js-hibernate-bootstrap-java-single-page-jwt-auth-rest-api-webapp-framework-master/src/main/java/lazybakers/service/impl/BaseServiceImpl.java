package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Base;
import lazybakers.model.repository.BaseRepository;
import lazybakers.service.BaseService;

@Service("baseService")
@Transactional
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	BaseRepository baseRepository;
	

	@Override
	public Integer createBase(String baseName, float price) {
		return baseRepository.createBase(baseName, price);
	}

	@Override
	public List<Base> getAllBase() {
		return baseRepository.getAllBase();
	}

	@Override
	public Base getBaseById(int baseId) {
		return baseRepository.getBaseById(baseId);
	}

}
