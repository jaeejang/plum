package org.plum.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.plum.model.system.User;
import org.plum.security.UserService;
import org.plum.service.SystemService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userjq")
public class UserControllerJqGrid {
	private static Logger log = LoggerFactory.getLogger(UserControllerJqGrid.class);
	@Autowired
	UserService userService;
	@Autowired
	SystemService systemService;

	@RequestMapping
	public String execute() {
		return "sys/user-jq";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("branches",systemService.loadBranch());
		return "sys/user_edit";
	}

	@RequestMapping("/list")
	@Pagination
	public @ResponseBody Paginator getList() {
		return new Paginator(userService.selectAllWithPagination());
	}

	@RequestMapping(value="edit/{username}", method = RequestMethod.GET)
	public String edit(@PathVariable String username, Model model) {
		model.addAttribute("user", userService.getUser(username));
		model.addAttribute("branches",systemService.loadBranch());
		return "sys/user_edit";
	}

	@RequestMapping(value="edit*", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("user") User user, 
			BindingResult result, Model model, HttpServletRequest request) {
		if (!result.hasErrors()){
			userService.saveOrUpdate(user);
			return "redirect:/user";
		}
		else{
			model.addAttribute("validation", ValidationUtil.hashErrors(result.getFieldErrors()));
			model.addAttribute("user",user);
			log.debug("validation occures, request context path " + request.getContextPath());
			return "forward:"  + request.getContextPath();
		}
	}

	@RequestMapping("delete/{username}")
	public String delete(@PathVariable String username, Model model) {
		userService.delete(username);
		model.addAttribute("result", "sucess");
		model.addAttribute("desc", "");
		return "sys/user";
	}


}
