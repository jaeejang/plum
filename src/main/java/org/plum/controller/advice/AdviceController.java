package org.plum.controller.advice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.plum.initial.PlumContext;
import org.plum.model.advice.Advice;
import org.plum.model.advice.AdviceComment;
import org.plum.model.advice.FileAttach;
import org.plum.model.system.User;
import org.plum.service.AdviceService;
import org.plum.service.FileAttachService;
import org.plum.service.SystemService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.ui.JsonModel;
import org.plum.tools.ui.JsonResult;
import org.plum.tools.ui.ResultType;
import org.plum.tools.ui.Utils;
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
@RequestMapping("/adv")
public class AdviceController {

	private static Logger log = LoggerFactory.getLogger(AdviceController.class);
	@Autowired
	AdviceService adviceService;

	@Autowired
	SystemService systemService;

	@Autowired
	FileAttachService fileAttachService;

	@Autowired
	PlumContext context;

	@RequestMapping
	public String execute(HttpServletRequest request) {
		context.setContext(request);
		User user = context.getUser();
		if (!user.isAdmin())
			return "forward:/adv/my";
		else
			return "forward:/adv/admin";
	}

	@RequestMapping("/my")
	public String showmy(Model model) {
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("branches", systemService.loadBranch());
		return "advice/advice";
	}

	@RequestMapping("/show")
	public String showList(Model model) {
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("branches", systemService.loadBranch());
		return "advice/advice_show";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("branches", systemService.loadBranch());
		return "advice/advice_admin";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("advice", new Advice());
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		return "advice/advice_edit";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("advice", adviceService.getAdvice(id));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("seasons", Utils.Seasons(4));
		return "advice/advice_edit";
	}

	@RequestMapping(value = { "/my/view/{id}", "/show/view/{id}" }, method = RequestMethod.GET)
	public String myview(@PathVariable int id, Model model) {
		model.addAttribute("advice", adviceService.getAdvice(id));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		return "advice/advice_view";
	}

	@RequestMapping(value = { "/list/{type}" })
	@Pagination
	public @ResponseBody Paginator getList(@PathVariable String type, HttpServletRequest request) {
		if (type.equals("all")) {
			return new Paginator(adviceService.selectAdvice(null, request.getParameterMap()));
		} 
		else if(type.equals("my")) {
			context.setContext(request);
			return new Paginator(
					adviceService.selectAdvice(context.getUser().getUsername(), request.getParameterMap()));
		}else {
			return new Paginator(adviceService.selectPublicAdvice(request.getParameterMap()));
		}
	}

	@RequestMapping(value = { "/view/{id}" }, method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model,HttpServletRequest request) {
		
		Advice advice  = adviceService.getAdvice(id);
		context.setContext(request);
		String username = context.getUser().getUsername();
		if(advice.getStatus().intValue() == 0 &&  username.equals(advice.getCrtusr())) {
			return "forward:/adv/edit/" + id;
		}
			
		model.addAttribute("advice", advice);
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());

		List<FileAttach> files = fileAttachService.getFileAttach(id, 0);
		model.addAttribute("files", files);
		

		
		List<AdviceComment> commets = adviceService.getCommentByAdvice(id);
		model.addAttribute("commets", commets);
		
		
		List<FileAttach> comm_files = new  ArrayList<FileAttach>();
		for (AdviceComment comm : commets) {
			comm_files.addAll(fileAttachService.getFileAttach(comm.getAdviceId(), 1));
		}
		model.addAttribute("comm_files", comm_files);

		return "advice/advice_view";
	}

	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	public String save(@ModelAttribute("advice") Advice advice, Model model, HttpServletRequest request) {
		advice.setCrtusr(context.getUser().getUsername());
		advice.setBrchno(context.getUser().getBrchno());
		advice.setCrttime(Calendar.getInstance().getTime());
		//advice.setStatus(1);
		adviceService.saveOrUpdateAdvice(advice);
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public JsonModel delete(@PathVariable int id, Model model) {
		JsonResult result = JsonResult.createInstance();
		if (adviceService.getAdvice(id) != null && adviceService.getAdvice(id).getStatus() != 0) {
			result.setType(ResultType.WARNING);
			result.addAttribute("message", "只允许删除编辑状态的建议");
		} else {
			int count = adviceService.deleteAdvice(id);
			result.setType(ResultType.SUCCESS);
			result.addAttribute("message", String.format("成功删除%d条记录", count));
		}
		return result;
	}
	
	/*
	 * 意见反馈
	 */
	@RequestMapping(value = { "/comment/{id}" }, method = RequestMethod.GET)
	public String comment(@PathVariable int id, Model model,HttpServletRequest request) {
		
		Advice advice  = adviceService.getAdvice(id);
		model.addAttribute("advice", advice);
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());

		List<FileAttach> files = fileAttachService.getFileAttach(id, 0);
		model.addAttribute("files", files);
		
		List<AdviceComment> commets = adviceService.getCommentByAdvice(id);
		model.addAttribute("commets", commets);
		
		
		List<FileAttach> comm_files = new  ArrayList<FileAttach>();
		for (AdviceComment comm : commets) {
			comm_files.addAll(fileAttachService.getFileAttach(comm.getAdviceId(), 1));
		}
		model.addAttribute("comm_files", comm_files);
		
		return "advice/advice_comment";
	}
	
	@RequestMapping(value = { "/comment" }, method = RequestMethod.POST)
	public String saveComment(@ModelAttribute("adviceComment") AdviceComment comment, 
			Model model,HttpServletRequest request) {
		context.setContext(request);
		comment.setCrttime(Calendar.getInstance().getTime());		
		comment.setCrtusr(context.getUser().getUsername());
		adviceService.saveOrUpdateComment(comment);

		return "redirect:" + request.getHeader("Referer");
	}
	

	@RequestMapping("/delComment/{id}")
	@ResponseBody
	public JsonModel deleteComment(@PathVariable int id, Model model) {
		JsonResult result = JsonResult.createInstance();
		int count=  adviceService.deleteAdviceComment(id);
		result.setType(ResultType.SUCCESS);
		result.addAttribute("message", String.format("成功删除%d条记录", count));
		return result;
	}

}
