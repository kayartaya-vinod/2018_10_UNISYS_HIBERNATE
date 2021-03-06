package hibernate.training.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	// since one brand has multiple products, we need a collection
	// type of reference to represent the same.
	// We can use List, Set, Bad, IdBag, ...
	// Annotated with @OneToMany
	// Join information can be obtained from the other associated class (Product)
	// or using @JoinColumn (ref: Category.java)

	@OneToMany(mappedBy = "brand") // "brand" field in the "Product" class
	private List<Product> products;

}
