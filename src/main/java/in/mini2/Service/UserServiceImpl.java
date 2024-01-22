package in.mini2.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.mini2.PasswordGeneratorUtils;
import in.mini2.Model.Country;
import in.mini2.Model.State;
import in.mini2.Model.User;
import in.mini2.Repo.CityRepo;
import in.mini2.Repo.CountryRepo;
import in.mini2.Repo.StateRepo;
import in.mini2.Repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	@Override
	public boolean addCustomer(User user) {
		
		if(userRepo.findByEmail(user.getEmail()) != null) return false;
		
		User user2 = userRepo.save(user);
		
		if(user2 != null) return true;
		
		return false;
		
	}
	@Override
	public List<Country> getCountries() {
		
		 
		List<Country> list = countryRepo.findAll();
		
		return list;
	}

	@Override
	public List<String> getStates(String countryname) {
		
		
		System.out.println("country = " + countryname);
		List<String> list = new ArrayList<>();
		
		try {
			
			list = stateRepo.findStateNamesByCountryName(countryname);			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}

	@Override
	public List<String> getCity(String city) {
		
		List<String> list = new ArrayList<>();
		
		try {
			
			 list = cityRepo.findCityNamesByStateName(city);
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public String sendEmail(String email){
		

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

	   String pass	= PasswordGeneratorUtils.generateRandomPassword();
		
		simpleMailMessage.setTo(email); 
		simpleMailMessage.setSubject("PassWord");
		simpleMailMessage.setText(pass);
		javaMailSender.send(simpleMailMessage); 
        
        return pass;
		
	}

	@Override
	public User checkCredentials(String email, String randomPass) {
		
		User user = null;
		User user2 = null;
			
		if(userRepo.findByRandompass(randomPass) == null) {
			
			 user2 = userRepo.findByEmailAndPwd(email,randomPass);
			 if(user2 != null)
			 return user2; 	 
			
		}
		
		user = userRepo.findByEmailAndRandompass(email, randomPass);
		if(user != null) return user;
		
	     return null;
	    
	}
	@Override
	public boolean updateCustomer(String randompass,String pwd) {
		
		
		User user = userRepo.findByRandompass(randompass);
		
		if(user == null) return false;
		
		user.setPwd(pwd);
		
		user.setRandompass(null);
		
		userRepo.save(user);
		return true;
		
	}
	
	
	
}
