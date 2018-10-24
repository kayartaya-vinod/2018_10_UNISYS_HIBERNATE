package hibernate.training.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Double salary;

	public Employee(String name, Double salary) {
		this.name = name;
		this.salary = salary;
	}

	@OneToOne(mappedBy = "ownedBy",  // Field "ownedBy" of class Laptop has the join information
			cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private Laptop laptop;
	
	// helper setter method for bidirectional association
	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
		laptop.setOwnedBy(this);
	}
	
	@ManyToMany (mappedBy="employees", cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	// @JoinTable(name="emp_skills", 
	// 		joinColumns=@JoinColumn(name="emp_id"),
	//		inverseJoinColumns = @JoinColumn(name="skill_id"))
	private List<TechnicalSkill> skills = new ArrayList<>();
	
	
}





