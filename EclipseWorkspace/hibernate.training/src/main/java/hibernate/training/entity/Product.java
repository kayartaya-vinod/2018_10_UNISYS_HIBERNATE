package hibernate.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private Double unitPrice;
	private Double discount;

	// entity associations

	// ManyToOne and OneToOne associations are EAGER by default
	// and are fetched using JOIN statements.
	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id") // foreign key
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
