package lazybakers.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Base {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "base_id")
	private int baseId;
	@Column(name = "base_name")
	private String baseName;
	private float price;
	
	public Base() {
		
	}
	
	public Base(String baseName, float price) {
		super();
		this.baseName = baseName;
		this.price = price;
	}

	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
