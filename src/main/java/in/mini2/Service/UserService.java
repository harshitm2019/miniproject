package in.mini2.Service;

import java.util.List;

import in.mini2.Model.Country;
import in.mini2.Model.State;
import in.mini2.Model.User;

public interface UserService {

	public boolean addCustomer(User user);
	
	public List<Country> getCountries();

	public List<String> getStates(String countryname);

	public List<String> getCity(String city);

	public String sendEmail(String email);

	public User checkCredentials(String email, String randomPass);
	
	public boolean updateCustomer(String randompass, String pwd);
	

	
	
}
