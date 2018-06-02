package org.plum.controller.advice;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.plum.initial.PlumContext;
import org.plum.model.advice.Advice;
import org.plum.model.system.User;
import org.plum.service.AdviceService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.ui.JsonModel;
import org.plum.tools.ui.Utils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adv")
public class AdviceController {
	
	private static Logger log = LoggerFactory.getLogger(AdviceController.class);
	@Autowired
	AdviceService adviceService;

	@Autowired 
	PlumContext context;
	
	@RequestMapping
	public String execute(HttpServletRequest request) {
		context.setContext(request);
		User user = context.getUser();
		if(!user.isAdmin())
			return "forward:/adv/my";
		else
			return "forward:/adv/admin";
	}
	
	@RequestMapping("/my")
	public String showmy() {
		return "advice/advice";
	}
	
	@RequestMapping("/show")
	public String showList() {
		return "advice/advice_show";
	}
	
	@RequestMapping("/admin")
	public String showAdmin() {
		return "advice/advice_admin";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("advice", new Advice());
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());	
		model.addAttribute("seasons",Utils.Seasons(4));
		return "advice/advice_edit";
	}

	@RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("advice", adviceService.getAdvice(id));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("seasons",Utils.Seasons(4));
		return "advice/advice_edit";
	}
	
	@RequestMapping(value = {"/my/view/{id}", "/show/view/{id}"}, method = RequestMethod.GET)
	public String myview(@PathVariable int id, Model model) {
		model.addAttribute("advice", adviceService.getAdvice(id));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		return "advice/advice_view";
	}
	
	@RequestMapping("/list/{type}")
	@Pagination
	public @ResponseBody Paginator getList(@PathVariable String type, 
			@RequestParam("search[value]") String search,
			HttpServletRequest request) {
		context.setContext(request);
		return new Paginator(adviceService.selectAdvice(context.getUser().getUsername(),search));
	}

	@RequestMapping(value = {"/view/{id}"}, method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model) {
		model.addAttribute("advice", adviceService.getAdvice(id));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		return "advice/advice_view";
	}

	@RequestMapping(value= {"/edit*", "add"}, method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("advice") Advice advice, BindingResult result, Model model, HttpServletRequest request) {
		if (!result.hasErrors()) {
			advice.setCrtusr(context.getUser().getUsername());
			advice.setBrchno(context.getUser().getBrchno());
			advice.setCrttime(Calendar.getInstance().getTime());
			advice.setStatus(1);
			adviceService.saveOrUpdateAdvice(advice);
			return "redirect:/adv/my";
		} else {
			model.addAttribute("validation", ValidationUtil.hashErrors(result.getFieldErrors()));
			model.addAttribute("advice", advice);
			log.debug("validation occures, request context path " + request.getContextPath());
			return "forward:"  + request.getContextPath();
		}
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		adviceService.deleteAdvice(id);
		model.addAttribute("result", "sucess");
		model.addAttribute("desc", "");
		return "advice/advice";
	}

	@RequestMapping("/vote")
	public @ResponseBody JsonModel vote(@RequestParam Integer adviceid){
		boolean ret = adviceService.saveAdviceVote(adviceid,context.getUser().getUsername());
		Advice advice = adviceService.getAdvice(adviceid);
		return JsonModel.createInstance().addAttribute("ret",ret ? 0:1).addAttribute("count",advice.getPolls());
	}
}
