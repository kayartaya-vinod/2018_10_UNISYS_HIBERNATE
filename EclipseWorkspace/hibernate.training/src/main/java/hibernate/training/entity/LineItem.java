package hibernate.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class LineItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Order order;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;

	@Column(name = "unit_price")
	private Double unitPrice;

	// overloaded constructor to quickly create a PrimaryKey value
	public LineItem(Integer orderId, Integer productId) {
		this.order = new Order();
		this.order.setId(orderId);
		this.product = new Product();
		this.product.setId(productId);
	}

	// helper function for setting the product price of the line_item
	public void setProduct(Product product) {
		this.product = product;
		this.setUnitPrice(product.getUnitPrice());
	}
}
