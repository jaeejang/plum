package org.plum.controller.system;

import org.plum.model.advice.Subject;
import org.plum.service.SystemService;
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
@RequestMapping("/subject")
public class SubjectController {


	@Autowired
	SystemService systemService;
	

	@RequestMapping
	public String execute() {
		return "sys/subject";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("subject", new Subject());
		return "sys/subject_edit";
	}

	@RequestMapping("/list")
	@Pagination
	public @ResponseBody Paginator getList() {
		return new Paginator(systemService.selectSubject(true));
	}

	@RequestMapping(value = "edit/{subjectid}", method = RequestMethod.GET)
	public String edit(@PathVariable int subjectid, Model model) {
		model.addAttribute("subject", systemService.getSubject(subjectid));
		return "sys/subject_edit";
	}

	@RequestMapping(value = "edit*", method = RequestMethod.POST)
	public String save(@ModelAttribute("subject") Subject subject, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			systemService.saveOrUpdateSubject(subject);
			return "redirect:/subject";
		} else {
			model.addAttribute("subject", subject);
		}
		return "sys/subject_edit";
	}

	@RequestMapping("delete/{id}")
	@ResponseBody
	public JsonModel delete(@PathVariable int id, Model model) {
		JsonResult result = JsonResult.createInstance();
		int count = systemService.deleteSubject(id);
		result.setType(ResultType.SUCCESS);
		result.addAttribute("message", String.format("成功删除%d条记录", count));
		return result;
	}
}
