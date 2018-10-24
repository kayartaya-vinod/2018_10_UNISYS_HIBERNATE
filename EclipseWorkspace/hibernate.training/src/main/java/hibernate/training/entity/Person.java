package hibernate.training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
// Strategy 2: One table for Student and another for Professor
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

// Strategy 3: One table for each super and subclasses, with primary key of
// super class table as foreign key (as well as primary key) in the subclass
// tables
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

	@Id
	private Integer id;
	private String name;

}
