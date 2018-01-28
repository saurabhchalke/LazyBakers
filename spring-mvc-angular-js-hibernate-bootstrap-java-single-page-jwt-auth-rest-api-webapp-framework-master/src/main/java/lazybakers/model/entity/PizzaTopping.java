package lazybakers.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizza_topping")
public class PizzaTopping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pizza_topping")
	private int pizzaToppingId;
	
	public PizzaTopping() {
		
	}
	
	public PizzaTopping(Pizza pizza, Topping topping) {
		super();
		this.pizza = pizza;
		this.topping = topping;
	}
	@ManyToOne
	private Pizza pizza;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Topping topping;
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public Topping getTopping() {
		return topping;
	}
	public void setTopping(Topping topping) {
		this.topping = topping;
	}

}
