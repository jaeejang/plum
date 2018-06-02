package org.plum.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	
	@RequestMapping
	public String  execute() {
		return "home";
	}
}
