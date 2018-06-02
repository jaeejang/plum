package org.plum.controller.system;

import javax.validation.Valid;

import org.plum.model.system.Func;
import org.plum.model.system.FuncExample;
import org.plum.service.PrivilegeService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.validation.ValidationUtil;
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
		FuncExample example = new FuncExample();
		return new Paginator(privilegeService.selectFunc(example));
	}

	@RequestMapping(value="edit/{funid}", method = RequestMethod.GET)
	public String edit(@PathVariable int funid, Model model) {
		model.addAttribute("func", privilegeService.getFunc(funid));
		return "sys/func_edit";
	}

	@RequestMapping(value="edit*", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("func") Func func, 
			BindingResult result, Model model) {
		if (!result.hasErrors()){
			privilegeService.saveOrUpdateFunc(func);
			return "redirect:/func";
		}
		else{
			model.addAttribute("validation", ValidationUtil.hashErrors(result.getFieldErrors()));
			model.addAttribute("func",func);
		}
		return "sys/func_edit";
	}

	@RequestMapping("delete/{funid}")
	public String delete(@PathVariable int funid, Model model) {
		privilegeService.deleteFunc(funid);
		model.addAttribute("result", "sucess");
		model.addAttribute("desc", "");
		return "sys/func";
	}
}
