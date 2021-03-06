package hibernate.training.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "customer_id") // whenever mapping a foreign key, use @ManyToOne
	private Customer customer;

	@Column(name = "order_date")
	private Date orderDate = new Date();

	private String status = "PENDING";

	// One order may contain one or more line-items
	@OneToMany(mappedBy = "order", // "order" field of "LineItem" class
			cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<LineItem> lineItems = new ArrayList<>();

	// helper function to do bidirectional association
	public void addLineItem(LineItem item) {
		this.lineItems.add(item); // add the line-item to this order's line-items
		item.setOrder(this); // make this order as the owner of the line-item supplied
	}
}
