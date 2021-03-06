package hibernate.training.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	// With OneToMany and ManyToMany @JoinColumn refers to the foreign key
	// in the table corresponding to the associated entity (Product class/ products
	// table)
	@OneToMany
	@JoinColumn(name = "category_id")
	private List<Product> products; // all products of this category
}
