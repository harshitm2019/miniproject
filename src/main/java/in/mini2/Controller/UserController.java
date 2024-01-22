package in.mini2.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.mini2.Model.Country;
import in.mini2.Model.User;
import in.mini2.Service.ThirdPartyApiService;
import in.mini2.Service.UserService;



@Controller
public class UserController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ThirdPartyApiService thirdPartyApiService;
	
	
	@GetMapping("/register")
	public String registerView(Model model){
		
		HashMap<String, Object> map = new HashMap<>();
		List<Country> list = userService.getCountries();
		
		map.put("user", new User());
		map.put("countries", list);
		model.addAllAttributes(map);
		return "registerView"; 
	}
	
	@PostMapping("/register")
	public String handleRegister(User user,Model model){
		
	   String passString = userService.sendEmail(user.getEmail());
	   user.setRandompass(passString);
		
		boolean status = userService.addCustomer(user);
		
		if(status){
			
			model.addAttribute("msgS", "Password Sent to Your mail");
		}
		else {
			
			return "redirect:register";
			
		}
		
		return "redirect:dashboard";
	}
	
	@PostMapping("/filter-state")
	public String filterState(@ModelAttribute("user") User user , Model model){
		

	    List<String> list = userService.getStates(user.getCountry());	 
		
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("states",list);
	    map.put("user", user);
	    model.addAllAttributes(map);
	    
		return "stateView";
	}
	
	@PostMapping("/filter-city")
	public String filterCity(@ModelAttribute("user") User user,Model model){
		
		List<String> list = userService.getCity(user.getState());
		System.out.println(user.getCity());
		HashMap<String, Object> map = new HashMap<>();
	    map.put("cities",list);
	    map.put("user", user);
	    model.addAllAttributes(map);
		
		return "cityView"; 
		
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		model.addAttribute("dashboard", "success");
		return "dashboard";
		
	}
	
	@GetMapping("/logout")
	public String logout(){
		
		return "redirect:login";
		
	}
	
	
	@GetMapping("/login")
	public String login(Model model){
		
		model.addAttribute("login", new User());
		return "loginView";
		
	}
	
	
	@PostMapping("/login")
	public String login(Model model,@ModelAttribute("login") User user){
		
		
	    User u = userService.checkCredentials(user.getEmail(),user.getRandompass());
			
		if(u != null && u.getPwd() == null)	
		return "changePass";
		
		else if(u != null && u.getRandompass() == null) 
		return "redirect:dashboard";	
		
		else 
		model.addAttribute("msg", "Wrong Credentials");	
		
		return "loginView";
		
	}
	
	@PostMapping("/updatePass")
	public String updatePass(User user,Model model) {
		
		
		boolean status = userService.updateCustomer(user.getRandompass(), user.getPwd());
		
		if(status) return "redirect:dashboard";
		
		else model.addAttribute("errmsg", "Wrong password");
		
		return "changePass";
		
	}
	
	@GetMapping(value = "/newQuote" , produces = MediaType.TEXT_PLAIN_VALUE)
	public String newQuote(Model model){
		
		Random random = new Random();
       
        int randomNumber = random.nextInt(10) + 1; 
		
		String quote = thirdPartyApiService.getDataFromApi("https://type.fit/api/quotes",randomNumber);
		
		model.addAttribute("quote", quote);
		
		return "quoteView";
		
	}
	
	        
}
