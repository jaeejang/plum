package org.plum.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.plum.model.system.User;
import org.plum.security.UserService;
import org.plum.service.PrivilegeService;
import org.plum.service.SystemService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.ui.JsonModel;
import org.plum.tools.ui.JsonResult;
import org.plum.tools.ui.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	SystemService systemService;

	@Autowired
	PrivilegeService  privilegeService;
	

	@RequestMapping
	public String execute(Model model) {
		model.addAttribute("branches",systemService.loadBranch());
		return "sys/user";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("branches",systemService.loadBranch());
		return "sys/user_edit";
	}

	@RequestMapping("/list")
	@Pagination
	public @ResponseBody Paginator getList(HttpServletRequest request) {
		return new Paginator(userService.selectAllWithPagination(request.getParameterMap()));
	}

	@RequestMapping(value="/edit/{username}", method = RequestMethod.GET)
	public String edit(@PathVariable String username, Model model) {
		User user = userService.getUser(username);
		model.addAttribute("user", userService.getUser(username));
		model.addAttribute("roles",privilegeService.selectRoles());
		model.addAttribute("user_roles",privilegeService.selectUserRoles(user));
		model.addAttribute("branches",systemService.loadBranch());
		return "sys/user_edit";
	}

	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user,  Model model, HttpServletRequest request) {
			userService.saveOrUpdate(user);
			if(request.getParameter("roles")!=null) {
					//int[] roleids = RequestUtils.getQueryParm(request.getParameterMap(), int[].class, "roles");
					int[] roleids =new int[request.getParameterValues("roles").length];
					for (int i = 0; i < request.getParameterValues("roles").length; i++) {
						String strid = request.getParameterValues("roles")[i];
						roleids[i] = Integer.parseInt(strid);
					}
					privilegeService.saveOrUpdateUserRole(user.getUsername(), roleids);
			}
			return "redirect:"  + request.getHeader("Referer");
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public JsonModel delete(@PathVariable String id, Model model) {
		JsonResult result = JsonResult.createInstance();
		int count = userService.delete(id);
		result.setType(ResultType.SUCCESS);
		result.addAttribute("message", String.format("成功删除%d条记录", count));
		return result;
	}


}
