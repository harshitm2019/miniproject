package in.mini2.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Country {
 
	 
	 @Id
	 private String countryname;
	 
	 
	 @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
	 List<State> states;


	public String getCountryname() {
		return countryname;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	 
	 
	 
}
