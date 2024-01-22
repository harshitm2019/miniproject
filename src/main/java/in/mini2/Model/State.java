package in.mini2.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class State{

	@Id
	private String statename;
	
	@JoinColumn(name = "countryname")
	@ManyToOne
	private Country country;
	
	@OneToMany(mappedBy = "state" ,cascade = CascadeType.ALL)
	private List<City> cities;
	
	
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	
}
