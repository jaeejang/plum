package org.plum.controller.system;

import org.plum.model.system.Func;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/func")
public class FuntionController {
	@Autowired
	PrivilegeService privilegeService;

	@RequestMapping
	public String execute() {
		return "sys/func";
	}
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("func", new Func());
		return "sys/func_edit";
	}
	

	@RequestMapping("/list")
	@Pagination
	public @ResponseBody Paginator getList() {
		return new Paginator(privilegeService.selectFuncs());
	}

	@RequestMapping(value="edit/{funid}", method = RequestMethod.GET)
	public String edit(@PathVariable int funid, Model model) {
		model.addAttribute("func", privilegeService.getFunc(funid));
		return "sys/func_edit";
	}

	@RequestMapping(value="edit*", method = RequestMethod.POST)
	public String save(@ModelAttribute("func") Func func, 
			BindingResult result, Model model) {
		if (!result.hasErrors()){
			privilegeService.saveOrUpdateFunc(func);
			return "redirect:/func";
		}
		else{
			model.addAttribute("func",func);
		}
		return "sys/func_edit";
	}

	@RequestMapping("delete/{id}")
	@ResponseBody
	public JsonModel delete(@PathVariable int id, Model model) {
		JsonResult result = JsonResult.createInstance();
		int count = privilegeService.deleteFunc(id);
		result.setType(ResultType.SUCCESS);
		result.addAttribute("message", String.format("成功删除%d条记录", count));
		return result;
	}
}
