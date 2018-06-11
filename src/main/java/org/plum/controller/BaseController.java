package org.plum.controller;

import javax.servlet.http.HttpServletRequest;

import org.plum.initial.PlumContext;
import org.plum.model.system.User;
import org.plum.tools.ui.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice({ "org.plum.controller.system", "org.plum.controller.advice","org.plum.controller" })
public class BaseController {

	@Autowired
	PlumContext context;

	private static Logger log = LoggerFactory.getLogger(BaseController.class);

	@ModelAttribute
	public void addAttribute(HttpServletRequest request, Model model) {
		Object sess = request.getSession().getAttribute("user");
		if (sess != null && sess instanceof User) {
			log.debug("get user session and privileges");
			
			if(request.getHeader("X-PJAX")!=null ) {
				model.addAttribute("pjax",true);
				log.debug("PJAX REQUEST");
			}
			else {
				model.addAttribute("pjax",false);
				context.setContext(request);
				TreeNode menu = context.getMenuTree();
				model.addAttribute("function", context.getLeef() == null ? "" : context.getLeef().getFuncname());
				model.addAttribute("menu", menu);
				model.addAttribute("_user", context.getUser());
			}
			model.addAttribute("dicts",context.getDicts());
			
		}
	}
}
