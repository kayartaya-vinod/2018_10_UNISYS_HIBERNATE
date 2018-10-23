package hibernate.training.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;

	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "streetAddress", column = @Column(name = "address")) 
	})
	private ContactInfo contactInfo = new ContactInfo(); 
	// not an associated entity, but a component.
	// the fields in ContactInfo.java are mapped to respective columns in
	// the "CUSTOMERS" table.

	public String getCity() {
		return contactInfo.getCity();
	}

	public String getCountry() {
		return contactInfo.getCountry();
	}

	public String getPhone() {
		return contactInfo.getPhone();
	}

	public String getState() {
		return contactInfo.getState();
	}

	public String getStreetAddress() {
		return contactInfo.getStreetAddress();
	}

	public void setCity(String city) {
		contactInfo.setCity(city);
	}

	public void setCountry(String country) {
		contactInfo.setCountry(country);
	}

	public void setPhone(String phone) {
		contactInfo.setPhone(phone);
	}

	public void setState(String state) {
		contactInfo.setState(state);
	}

	public void setStreetAddress(String streetAddress) {
		contactInfo.setStreetAddress(streetAddress);
	}

	
}
