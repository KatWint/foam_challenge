package com.katwdojo.experiments.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.katwdojo.experiments.models.Image;
import com.katwdojo.experiments.models.LoginUser;
import com.katwdojo.experiments.models.User;
import com.katwdojo.experiments.services.ImageService;
import com.katwdojo.experiments.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private ImageService imageServ;
	
	@GetMapping("/")
	public String index(@ModelAttribute("newUser")User newUser, @ModelAttribute("newLogin")LoginUser loginuser) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid@ModelAttribute("newUser") User newUser, 
			BindingResult result, HttpSession session, @ModelAttribute("newLogin")LoginUser loginuser) {
		userServ.validate(newUser, result);
		if(result.hasErrors()) {
			return "index.jsp";
     }
		userServ.registerUser(newUser);
		session.setAttribute("loggedInUser", newUser);
			return "redirect:/home";
 }
 
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
         BindingResult result, HttpSession session, @ModelAttribute("newUser") User newUser) {
		userServ.authenticateUser(newLogin, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		User loggedInUser=userServ.findByUserName(newLogin.getUserName());
		session.setAttribute("loggedInUser", loggedInUser);
		return "redirect:/home";
 }
 
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/experiments")
	public String experiments(HttpSession session, Model viewImages) {
		viewImages.addAttribute("allImages", imageServ.getAllImages());
		if(session.getAttribute("loggedInUser")!=null) {
			return "experiments.jsp";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/home")
	public String home(HttpSession session){
		if(session.getAttribute("loggedInUser")!=null) {
			return "home.jsp";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/nonfoaming")
	public String nonFoaming(HttpSession session, Model model,Sort nonfoaming) {
		model.addAttribute("allImages", imageServ.findGroup(nonfoaming));
		if(session.getAttribute("loggedInUser")!=null) {
			return "nonfoaming.jsp";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/foaming")
	public String foaming(HttpSession session, Model model, Sort foaming) {
		model.addAttribute("allImages", imageServ.findGroup(foaming));
		if(session.getAttribute("loggedInUser")!=null) {
			return "nonfoaming.jsp";
		}else {
			return "redirect:/";
		}
	}
	@GetMapping("/unclassified")
	public String unclassified(HttpSession session, Model model, Sort unclassified) {
		model.addAttribute("allImages", imageServ.findGroup(unclassified));
		if(session.getAttribute("loggedInUser")!=null) {
			return "nonfoaming.jsp";
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/classify/{id}")
	public String classify(HttpSession session, @PathVariable Long id, Model model) {
		model.addAttribute("newClassification", imageServ.findOne(id));
		if(session.getAttribute("loggedInUser")!=null) {
			return "classify.jsp";
		}else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/saveimage")
    public String saveImage(@RequestParam("file") MultipartFile file) {
		imageServ.saveImage(file);
		return "redirect:/experiments";
    }
	
	@PutMapping("/classify")
	public String update(@ModelAttribute("newClassification")Image image) {
		imageServ.updateImage(image);
		return "redirect:/experiments";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
