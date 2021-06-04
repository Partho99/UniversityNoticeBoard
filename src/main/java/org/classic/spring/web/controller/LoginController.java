package org.classic.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.classic.spring.web.dao.User;
import org.classic.spring.web.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private UsersService usersSevice;

	@Autowired
	public void setUsersSevice(UsersService usersSevice) {
		this.usersSevice = usersSevice;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/loggedout")
	public String showLoggedout() {
		return "loggedout";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersSevice.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {

		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST)
	public String doCreate(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			List<ObjectError> err = bindingResult.getAllErrors();
			for (ObjectError error : err) {
				System.out.println(error.getDefaultMessage());
			}

			return "newaccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (usersSevice.exists(user.getUsername())) {
			bindingResult.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		try {
			usersSevice.createUser(user);
		} catch (DuplicateKeyException e) {
			bindingResult.rejectValue("username", "DuplicateKey.user.userrname", "This username already exist ");
			return "newaccount";
		}

		return "account-created";
	}
}
