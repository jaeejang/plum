package org.plum.controller.advice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.plum.initial.PlumContext;
import org.plum.model.advice.Advice;
import org.plum.model.advice.AdviceComment;
import org.plum.model.advice.FileAttach;
import org.plum.model.system.User;
import org.plum.service.AdviceService;
import org.plum.service.FileAttachService;
import org.plum.service.RequestUtils;
import org.plum.service.SystemService;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Paginator;
import org.plum.tools.ui.JsonModel;
import org.plum.tools.ui.JsonResult;
import org.plum.tools.ui.ObjectMap;
import org.plum.tools.ui.ResultType;
import org.plum.view.ExcelExportView;
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
import org.springframework.web.servlet.ModelAndView;

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
	

	@RequestMapping("/feedback")
	public String feedback(Model model,HttpServletRequest request) {
		//model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("branches", systemService.loadBranch());
		context.setContext(request);
		model.addAttribute("adviceBranch", context.getUser().getBrchno());
		return "advice/advice_feedback";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("advice", new Advice());
		model.addAttribute("subjects", systemService.selectSubject(false));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		return "advice/advice_edit";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("advice", adviceService.getAdvice(id));
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());
		model.addAttribute("subjects", systemService.selectSubject(false));
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
			return new Paginator(adviceService.selectAdminAdvice(request.getParameterMap()));
		} else if (type.equals("my")) {
			context.setContext(request);
			return new Paginator(
					adviceService.selectAdvice(context.getUser().getUsername(), request.getParameterMap()));
		} else {
			return new Paginator(adviceService.selectPublicAdvice(request.getParameterMap()));
		}
	}

	@RequestMapping(value = { "/export/{type}" })
	public ModelAndView  export(@PathVariable String type, HttpServletRequest request) {
		Map<String, Object> map  = new LinkedHashMap<>();
		map.put("name", "意见建议");
		if (type.equals("all")) {
			map.put("data", adviceService.selectAdvice(null, request.getParameterMap()));
		} else if (type.equals("my")) {
			context.setContext(request);
			map.put("data",adviceService.selectAdvice(context.getUser().getUsername(), request.getParameterMap()));
		} else {
			map.put("data",adviceService.selectPublicAdvice(request.getParameterMap()));
		}
		List mapper = new ArrayList<ObjectMap>() {{
			add(new ObjectMap("ID","id"));
			add(new ObjectMap("建议类别","catalog","advice_catalog"));
			add(new ObjectMap("牵头部门","leaddepna"));
			add(new ObjectMap("标题","summary"));
			add(new ObjectMap("内容","content"));
			add(new ObjectMap("提出人","crtusrna"));
			add(new ObjectMap("提出机构","brchna"));
			add(new ObjectMap("提出时间","crttime"));
		}};
		map.put("map", mapper);
		
		return new ModelAndView(new ExcelExportView(), map);
		
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

		List<AdviceComment> commets = adviceService.getCommentByAdvice(id);
		model.addAttribute("commets", commets);

		/*
		 * List<FileAttach> comm_files = new ArrayList<FileAttach>(); for (AdviceComment
		 * comm : commets) {
		 * comm_files.addAll(fileAttachService.getFileAttach(comm.getAdviceId(), 1)); }
		 * model.addAttribute("comm_files", comm_files);
		 */
		return "advice/advice_view";
	}

	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	public String save(@ModelAttribute("advice") Advice advice, Model model, HttpServletRequest request) {
		boolean added = false;
		if (advice.getId() == null || advice.getId() == 0) {
			added = true;
			advice.setCrtusr(context.getUser().getUsername());
			advice.setBrchno(context.getUser().getBrchno());
			advice.setCrttime(Calendar.getInstance().getTime());
		}
		// advice.setStatus(1);
		adviceService.saveOrUpdateAdvice(advice);

		if (advice.getStatus() == 0)
			return "redirect:/adv/edit/" + advice.getId();
		else
			return "redirect:/adv/my";
	}

	/*
	 * 删除
	 */
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
	public String comment(@PathVariable int id, Model model, HttpServletRequest request) {

		Advice advice = adviceService.getAdvice(id);
		model.addAttribute("advice", advice);
		model.addAttribute("adviceBranch", adviceService.selectAdviceBranch());

		List<FileAttach> files = fileAttachService.getFileAttach(id, 0);
		model.addAttribute("files", files);

		List<AdviceComment> commets = adviceService.getCommentByAdvice(id);
		model.addAttribute("commets", commets);

		List<FileAttach> comm_files = new ArrayList<FileAttach>();
		for (AdviceComment comm : commets) {
			comm_files.addAll(fileAttachService.getFileAttach(comm.getAdviceId(), 1));
		}
		model.addAttribute("comm_files", comm_files);

		return "advice/advice_comment";
	}

	/*
	 * 保存反馈
	 */
	@RequestMapping(value = { "/comment" }, method = RequestMethod.POST)
	public String saveComment(@ModelAttribute("adviceComment") AdviceComment comment, Model model,
			HttpServletRequest request) {
		context.setContext(request);
		comment.setCrttime(Calendar.getInstance().getTime());
		comment.setCrtusr(context.getUser().getUsername());
		comment.setCrtbrch(context.getUser().getBrchno());
		adviceService.saveOrUpdateComment(comment);
		
		Boolean isPublic = RequestUtils.getQueryParm(request.getParameterMap(), Boolean.class, "isPublic");
		
		Advice advice = adviceService.getAdvice(comment.getAdviceId());
		if(advice != null) {
			advice.setCmttime(Calendar.getInstance().getTime());
			advice.setPub(isPublic);
			adviceService.saveOrUpdateAdvice(advice);
		}

		return "redirect:" + request.getHeader("Referer");
	}

	/*
	 * 反馈满意度
	 */
	@RequestMapping("/survey")
	@ResponseBody
	public JsonModel survey(HttpServletRequest request, Model model) {
		JsonResult result = JsonResult.createInstance();
		int comId = RequestUtils.getQueryParm(request.getParameterMap(), Integer.class, "id");
		Integer satisfy = RequestUtils.getQueryParm(request.getParameterMap(), Integer.class, "satisfy");
		AdviceComment comment = adviceService.getComment(comId);
		if (comment != null) {
			comment.setSatisfy(satisfy);
			adviceService.saveOrUpdateComment(comment);
			result.setType(ResultType.SUCCESS);
		} else {
			result.setType(ResultType.ERROR);
			result.addAttribute("message", "没有找到需要更新的记录");
		}
		return result;
	}

	/*
	 * 删除反馈
	 */
	@RequestMapping("/delComment/{id}")
	@ResponseBody
	public JsonModel deleteComment(@PathVariable int id, Model model) {
		JsonResult result = JsonResult.createInstance();
		int count = adviceService.deleteAdviceComment(id);
		result.setType(ResultType.SUCCESS);
		result.addAttribute("message", String.format("成功删除%d条记录", count));
		return result;
	}
	
	/*
	 * 点赞
	 */
	@RequestMapping("/poll")
	@ResponseBody
	public JsonModel poll(HttpServletRequest request, Model model) {
		JsonResult result = JsonResult.createInstance();
		Integer id = RequestUtils.getQueryParm(request.getParameterMap(), Integer.class, "id");
		Integer type = RequestUtils.getQueryParm(request.getParameterMap(), Integer.class, "type");
		
		if(id == null || type == null) {
			result.setType(ResultType.ERROR);
			result.addAttribute("message", "参数传递错误");
		}
		else {
			context.setContext(request);
			boolean ret  =   adviceService.saveAdviceVote(id,context.getUser().getUsername(),type);
			
			if(ret) {
				result.setType(ResultType.SUCCESS);
				Advice advice = adviceService.getAdvice(id);
				result.addAttribute("polls", advice.getPolls());
				result.addAttribute("polldown", advice.getPolldown());
			}else {
				result.setType(ResultType.WARNING);
				result.addAttribute("message","不要重复点赞");
			}
		}
		return result;

	}
	
	

}
