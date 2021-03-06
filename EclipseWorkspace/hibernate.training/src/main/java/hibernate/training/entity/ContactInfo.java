package hibernate.training.entity;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable // by itself, this is not an entity, but becomes part of another entity
public class ContactInfo {

	private String phone;
	// @Column(name = "address") // this is not helpful if two tables have two
	// different column names
	private String streetAddress;
	private String city;
	private String state;
	private String country;

}
