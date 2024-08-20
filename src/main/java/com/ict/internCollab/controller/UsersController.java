package com.ict.internCollab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ict.internCollab.entity.Users;
import com.ict.internCollab.entity.UsersType;
import com.ict.internCollab.services.UsersService;
import com.ict.internCollab.services.UsersTypeService;

import jakarta.validation.Valid;

@Controller
public class UsersController {
	
	//fields for service
	private final UsersTypeService usersTypeService;
	private final UsersService usersService;

	//constructor injection
	@Autowired   //autowired is optional
	public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
		
		this.usersTypeService = usersTypeService;
		this.usersService = usersService;
	}
	
	//Show user registration form
	// we will use model to pre-populate some form data we need here
	@GetMapping("/register")
	public String register(Model model) {
		
		List<UsersType> usersTypes = usersTypeService.getAll();
													//Fetching User Types: we use the usersTypeService to retrieve 
													//a list of all user types and stores them in a List<UsersType> object
													//named usersTypes
							//key			//value
		model.addAttribute("getAllTypes", usersTypes);
												//Adds the list of user types to the model with the key "getAllTypes". 
												//This data will be available to the view (registration.html form) for 
												//displaying options in a dropdown menu.
		
						//key	//value
		model.addAttribute("user",new Users());  //new empty user created
								//Adds a new, empty Users object to the model with the key "user". 
								//This object will be bound to the registration.html form, allowing the user 
								//to fill in the details. when submit is clicked into this empty new object
		
		return "register";   //returning register.html page
	}

// before same email error
/* 
	@PostMapping("/register/new")
	public String userRegistration(@Valid Users users) {

		System.out.println("User::"+users);

		usersService.addNew(users);

		return "dashboard";
		
	}
 */
	
	@PostMapping("/register/new")
	public String userRegistration(@Valid Users users,Model model) {
		
		
		//System.out.println("User::"+users);
		
		Optional<Users> optionalUsers =  usersService.getUserByEmail(users.getEmail());
		
		if(optionalUsers.isPresent()) {
			model.addAttribute("Error","Email already registered, try to register with other email.");
			
			List<UsersType> usersTypes = usersTypeService.getAll();
			model.addAttribute("getAllTypes", usersTypes);
			model.addAttribute("user",new Users());
			return "register";
		}
		
		usersService.addNew(users);
		
		return "dashboard";
		
	}	
	

}
