package yourwebproject2.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PizzaTopping {
	@EmbeddedId
	private PizzaToppingEmbedabble pizzaToppingEmbedabble;

	public PizzaToppingEmbedabble getPizzaToppingEmbedabble() {
		return pizzaToppingEmbedabble;
	}

	public void setPizzaToppingEmbedabble(PizzaToppingEmbedabble pizzaToppingEmbedabble) {
		this.pizzaToppingEmbedabble = pizzaToppingEmbedabble;
	}

}
