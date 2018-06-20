package org.plum.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.plum.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	SystemService systemService;
	
	@RequestMapping
	public String  execute(Model model) {
		
		model.addAttribute("subjects", systemService.selectSubject(false));
		return "home";
	}
}
