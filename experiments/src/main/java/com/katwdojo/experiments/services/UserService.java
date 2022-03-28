package com.katwdojo.experiments.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.katwdojo.experiments.models.LoginUser;
import com.katwdojo.experiments.models.User;
import com.katwdojo.experiments.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;

	public void validate(User newUser, BindingResult errors) {
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			errors.rejectValue("password","Mismatch","Password does not match!!" );
		}
		if(userRepo.findByUserName(newUser.getUserName()).orElse(null)!=null) {
			errors.rejectValue("email", "unique","Email is already taken!");
			System.out.println(userRepo.findByUserName(newUser.getUserName()));
		}
		
	}

	public User registerUser(User newUser) {
		String hashedPass=BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPass);
		userRepo.save(newUser);
		return null;
	}

	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName).orElse(null);
	}

	public User findById(Long id) {
		return userRepo.findById(id).orElse(null);
		
	}

	public boolean authenticateUser(LoginUser newLogin, Errors errors) {
		User user= userRepo.findByUserName(newLogin.getUserName()).orElse(null);
		if(user==null) {
			errors.rejectValue("userName", "missingUserName","Username not found!");
			return false;
		}else {
			if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
				errors.rejectValue("password", "Matches", "Invalid Password!");
				return false;
			}
		}

		return true;
	}

}

