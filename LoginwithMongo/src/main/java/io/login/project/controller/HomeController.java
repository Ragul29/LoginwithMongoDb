package io.login.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.login.project.model.Customer;
import io.login.project.model.User;
import io.login.project.repository.CustomerRepository;
import io.login.project.repository.Userrepository;

@Controller
public class HomeController {
	
	@Autowired
	private final CustomerRepository customerRepository; 
	
	@Autowired
	
	private BCryptPasswordEncoder encoder;
	
	private final Userrepository userrep;
	
	  @Autowired
	    public HomeController(Userrepository userrep,CustomerRepository customerRepository) {
	        this.userrep = userrep;
	        this.customerRepository=customerRepository;
	    }

	  
	  @PostMapping("/user/query")
	    public String addStudent(@Validated Customer customer, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "landing";
	        }

	        customerRepository.save(customer);
	        return "redirect:/";
	    }
	
//	@GetMapping("/login")
//	public String Login()
//	{
//		return "landing";
//	}
	  
//	  @GetMapping("/logout")
//	    public String Logout() {
//	        return "logout";
//	    }  
	  @GetMapping("/login")
	    public String Login() {
	        return "landing";
	    }  
	  
	  @GetMapping("/")
	    public String FirstPage() {
		  
		 // model.addAttribute("user", userrep.findAll());
	        return "mybio";
	    }  
	   
	   @GetMapping("/register")
	    public String Home(User user) {
	        return "registration";
	    }  
	 @PostMapping("/registration-success")
	    public String addStudent(User user, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "registration";
        }
	       
		 
	        User check=userrep.findByusername(user.getUsername());
//	        String ssi=user.getUsername();
//	        if (ssi.isEmpty()) {
//	        	model.addAttribute("empty", "empty");
//	          //  result.rejectValue("username", null, "There is already an account registered with that name");
//	            return "registration";
//	        }
	        
	        if (check != null) {
	        	model.addAttribute("username", "username");
	          //  result.rejectValue("username", null, "There is already an account registered with that name");
	            return "registration";
	        }
		
	        user.setPassword(encoder.encode(user.getPassword()));

	        userrep.save(user);
	       
	        return "registration-success";
	    }
	

//	@GetMapping("/")
//		public String Home(Model model)
//		{
//	
//		// model.addAttribute("user", userrep.findAll());
//		return "login";
//		} 
	 
//	@GetMapping("/")
//	public String GetUser(Model model)
//	{
//	
//	model.addAttribute("user", userrep.findAll());
//		return "login";
//	}
}
