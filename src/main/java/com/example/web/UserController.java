package com.example.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.DAO.UserRepository;
import com.example.entities.User;

@RestController
public class UserController {
	
	 @Autowired
	    private UserDetailsService userDetailsService;
	 
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/user")
	public List<User> get()
            throws Exception {
		
		 List<User> users = userRepo.findAll();
		

        return users;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user)
            throws Exception {
		

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        HttpHeaders responseHeaders = new HttpHeaders();
      
        responseHeaders.set("MyResponseHeader", "MyValue");
 

        return new ResponseEntity<String>("connecteed", responseHeaders, HttpStatus.CREATED);

    }
	
	 @GetMapping("/logout")
	    public String logout(HttpServletRequest request) throws ServletException {
	        request.logout();
	        return "redirect:/login";
	    }
	
	
	
	
	
}
