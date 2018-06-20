package org.plum.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.plum.model.system.Role;
import org.plum.service.PrivilegeService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.ui.JsonModel;
import org.plum.tools.ui.JsonResult;
import org.plum.tools.ui.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	PrivilegeService privilegeService;

	@RequestMapping
	public String execute() {
		return "sys/role";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("role", new Role());
		return "sys/role_edit";
	}

	@RequestMapping("/list")
	@Pagination
	public @ResponseBody Paginator getList() {
		return new Paginator(privilegeService.selectRoles());
	}

	@RequestMapping(value = "edit/{roleid}", method = RequestMethod.GET)
	public String edit(@PathVariable int roleid, Model model) {
		model.addAttribute("role", privilegeService.getRole(roleid));
		return "sys/role_edit";
	}

	@RequestMapping(value = "edit*", method = RequestMethod.POST)
	public String save(@ModelAttribute("role") Role role, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			privilegeService.saveOrUpdateRole(role);
			return "redirect:/role";
		} else {
			model.addAttribute("role", role);
		}
		return "sys/role_edit";
	}

	@RequestMapping("delete/{id}")
	@ResponseBody
	public JsonModel delete(@PathVariable int id, Model model) {
		JsonResult result = JsonResult.createInstance();
		int count = privilegeService.deleteRole(id);
		result.setType(ResultType.SUCCESS);
		result.addAttribute("message", String.format("成功删除%d条记录", count));
		return result;
	}

	@RequestMapping(value = "func/{roleid}", method = RequestMethod.GET)
	public String setRoleFunc(@PathVariable int roleid, Model model) {
		model.addAttribute("role", roleid);
		model.addAttribute("funcs", StringUtils.join(privilegeService.getRolefuncs(roleid),','));
		return "sys/role_func";
	}

	@RequestMapping(value = "func/{roleid}", method = RequestMethod.POST)
	public String saveRoleFunc(@RequestParam int[] funcs, @PathVariable int roleid, Model model) {
		privilegeService.saveRolefunc(roleid, funcs);

		model.addAttribute("role", roleid);
		model.addAttribute("funcs", StringUtils.join(privilegeService.getRolefuncs(roleid),','));
		return "sys/role_func";
	}
	
	@RequestMapping(value = "user/{roleid}", method = RequestMethod.GET)
	public String roleUsers(@PathVariable int roleid, Model model) {
		model.addAttribute("role", roleid);
		return "sys/role_user";
	}
	
	@RequestMapping(value = "user/list/{roleid}")
	@Pagination
	public @ResponseBody Paginator getUserList(@PathVariable int roleid) {
		Role role = privilegeService.getRole(roleid);
		return new Paginator(privilegeService.selectRoleUsers(role));
	}
	
}
