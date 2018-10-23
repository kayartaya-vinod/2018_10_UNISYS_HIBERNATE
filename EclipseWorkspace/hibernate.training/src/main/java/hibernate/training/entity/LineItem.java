package hibernate.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "line_items")
public class LineItem {

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;

	@Column(name = "unit_price")
	private Double unitPrice;

	// helper function for setting the product price of the line_item
	public void setProduct(Product product) {
		this.product = product;
		this.setUnitPrice(product.getUnitPrice());
	}
}