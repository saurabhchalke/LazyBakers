package lazybakers.model.repository;

import java.util.List;

import lazybakers.model.entity.Base;

public interface BaseRepository {
	
	public Integer createBase(String baseName, float price);
	
	public List<Base> getAllBase();
	
	public Base getBaseById(int baseId);
	
}
