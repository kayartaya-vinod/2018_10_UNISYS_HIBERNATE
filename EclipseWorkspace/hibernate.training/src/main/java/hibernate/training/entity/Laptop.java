package hibernate.training.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Laptop {
	@Id
	@Column(name = "serial_number")
	private String serialNumber;
	private String make;
	private String model;

	public Laptop(String serialNumber, String make, String model) {
		this.serialNumber = serialNumber;
		this.make = make;
		this.model = model;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "emp_id", unique = true) // unique=true makes this column one-to-one
	private Employee ownedBy; // foreign key will be associated as ManyToOne

}
