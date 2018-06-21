package org.plum.controller.advice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.plum.initial.PlumContext;
import org.plum.model.advice.Advice;
import org.plum.model.advice.AdviceComment;
import org.plum.model.advice.FileAttach;
import org.plum.model.advice.Subject;
import org.plum.service.AdviceService;
import org.plum.service.FileAttachService;
import org.plum.service.SystemService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.ui.ObjectMap;
import org.plum.view.ExcelExportView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tpc")
public class TopicController {
	protected final static Logger log = LoggerFactory.getLogger(TopicController.class);

	@Autowired
	PlumContext context;

	@Autowired
	FileAttachService fileAttachService;
	
	@Autowired
	SystemService systemService;

	@Autowired
	AdviceService adviceService;

	@RequestMapping("/{id}")
	public String execute(@PathVariable int id, HttpServletRequest request, Model model) {
		List<Subject> subjects = systemService.selectSubject(false);
		model.addAttribute("subjects", subjects);
		model.addAttribute("subjectid", id);
		return "advice/topic_show";
	}

	@RequestMapping("/my")
	public String my(Model model) {
		List<Subject> subjects = systemService.selectSubject(false);
		model.addAttribute("subjects", subjects);
		return "advice/topic";
	}

	@RequestMapping("/show")
	public String show(Model model) {
		List<Subject> subjects = systemService.selectSubject(false);
		model.addAttribute("branches", systemService.loadBranch());
		model.addAttribute("subjects", subjects);
		return "advice/topic_show";
	}

	@RequestMapping(value = { "/list/{type}" })
	@Pagination
	public @ResponseBody Paginator getList(@PathVariable String type, HttpServletRequest request) {
		if("my".equals(type)) {
			context.setContext(request);
			return new Paginator(adviceService.selectTopic(context.getUser().getUsername(),request.getParameterMap()));
		}else
			return new Paginator(adviceService.selectPublicTopic(request.getParameterMap()));

	}

	@RequestMapping(value = { "/view/{id}" }, method = RequestMethod.GET)
	public String view(@PathVariable int id, Model model, HttpServletRequest request) {

		Advice advice = adviceService.getAdvice(id);
		context.setContext(request);
		String username = context.getUser().getUsername();
		if (advice.getStatus().intValue() == 0 && username.equals(advice.getCrtusr())) {
			return "forward:/adv/edit/" + id;
		}

		model.addAttribute("advice", advice);
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());

		List<FileAttach> files = fileAttachService.getFileAttach(id, 0);
		model.addAttribute("files", files);

		return "advice/topic_view";
	}


	@RequestMapping(value = { "/export/{type}" })
	public ModelAndView  export(@PathVariable String type, HttpServletRequest request) {
		Map<String, Object> map  = new LinkedHashMap<>();
		map.put("name", "专题建议");
		if("my".equals(type)) {
			context.setContext(request);
			map.put("data", adviceService.selectTopic(context.getUser().getUsername(),request.getParameterMap()));
		}else
			map.put("data", adviceService.selectPublicTopic(request.getParameterMap()));

		List mapper = new ArrayList<ObjectMap>() {{
			add(new ObjectMap("ID","id"));
			add(new ObjectMap("专题","topic"));
			add(new ObjectMap("标题","summary"));
			add(new ObjectMap("内容","content"));
			add(new ObjectMap("提出人","crtusrna"));
			add(new ObjectMap("提出机构","brchna"));
			add(new ObjectMap("提出时间","crttime"));
		}};
		map.put("map", mapper);
		
		return new ModelAndView(new ExcelExportView(), map);
		
	}
}
