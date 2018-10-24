package hibernate.training.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tech_skills")
public class TechnicalSkill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	public TechnicalSkill(String name) {
		this.name = name;
	}

	// one-to-many from both entities (Employee, Laptop) is considered as many-to-many
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "emp_skills", 
		joinColumns = @JoinColumn(name = "skill_id"), 
		inverseJoinColumns = @JoinColumn(name = "emp_id"))
	private List<Employee> employees; // list of all employees with "this" skill

	// helper function for bidirectional association
	public void addEmployee(Employee employee) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(employee);
		employee.getSkills().add(this);
	}
}
