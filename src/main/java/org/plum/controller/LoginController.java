package org.plum.controller;

import javax.servlet.http.HttpServletRequest;

import org.plum.initial.PlumContext;
import org.plum.model.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired 
	PlumContext context;

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String login(@RequestParam(name = "error",required=false) String error, Model model, HttpServletRequest request) {
		context.setContext(request);
		User user = context.getUser();
		if(user != null) {
			return "forward:/home";
		}
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		return "login";
	}

}
