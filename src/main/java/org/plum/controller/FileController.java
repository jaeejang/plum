package org.plum.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.plum.model.advice.FileAttach;
import org.plum.service.FileAttachService;
import org.plum.tools.ui.FileMeta;
import org.plum.tools.ui.JsonResult;
import org.plum.tools.ui.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileAttachService fileAttachService;

	private static Logger log = LoggerFactory.getLogger(FileController.class);

	/***************************************************
	 * URL: /file/upload upload(): receives files
	 * 
	 * @param request
	 *            : MultipartHttpServletRequest auto passed
	 * @param response
	 *            : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody JsonResult upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		int sourceid = 0;
		if (request.getParameter("sourceid") != null && NumberUtils.isCreatable(request.getParameter("sourceid"))) {
			sourceid = NumberUtils.createInteger(request.getParameter("sourceid"));
		}
		// 2. get each file
		while (itr.hasNext()) {
			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			log.info(mpf.getOriginalFilename() + " uploaded! " + files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setName(mpf.getOriginalFilename());
			fileMeta.setSize(String.valueOf(mpf.getSize()));
			fileMeta.setThumbnailUrl(null);

			try {
				String path = getUploadFolder() + File.separator + UUID.randomUUID();
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(path));
				FileAttach attach = new FileAttach();
				attach.setAttachtype(0);
				attach.setFilename(mpf.getOriginalFilename());
				attach.setPath(path);
				attach.setFiletype(mpf.getContentType());
				if(sourceid > 0)
					attach.setSourceid(sourceid);
				attach.setFilesize(mpf.getSize() / 1024 + " Kb");
				fileAttachService.saveOrUpdateFileAttach(attach);

				fileMeta.setId(attach.getId());
				fileMeta.setUrl(request.getContextPath() + "/file/get/" + attach.getId());
				fileMeta.setDeleteUrl(request.getContextPath() + "/file/get/" + attach.getId());

			} catch (IOException e) {
				e.printStackTrace();
			}
			files.add(fileMeta);
		}
		JsonResult result = JsonResult.createInstance();
		result.addAttribute("files", files);
		return result;
	}

	/***************************************************
	 * URL: /file/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String value) {
		if (NumberUtils.isCreatable(value)) {
			FileAttach attach = fileAttachService.getFileAttach(NumberUtils.createInteger(value));
			try {
				File file = new File(attach.getPath());

				if (file.exists()) {
					response.setContentType(attach.getFiletype());
					response.setHeader("Content-disposition", "attachment; filename=\"" + attach.getFilename() + "\"");
					FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
				}
				// FileCopyUtils.copy( response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value = "/files/{value}", method = RequestMethod.GET)
	public @ResponseBody JsonResult getFiles(HttpServletResponse response, @PathVariable String value) {
		if (NumberUtils.isCreatable(value)) {
			LinkedList<FileMeta> files = new LinkedList<FileMeta>();
			List<FileAttach> attach = fileAttachService.getFileAttach(NumberUtils.createInteger(value), 0);
			for (FileAttach fileAttach : attach) {
				FileMeta e  = new FileMeta();
				e.setId(fileAttach.getId());
				e.setName(fileAttach.getFilename());
				e.setSize(fileAttach.getFilesize());
				files.add(e);
			}
			JsonResult ret = JsonResult.createInstance();
			ret.addAttribute("files", files);
			return ret;			
		}
		return null;
	}
	
	

	@RequestMapping(value = "/get/{value}", method = RequestMethod.DELETE)
	public @ResponseBody JsonResult delete(HttpServletResponse response, @PathVariable String value) {
		JsonResult json = JsonResult.createInstance();
		if (NumberUtils.isCreatable(value)) {
			int id = NumberUtils.createInteger(value);
			FileAttach attach = fileAttachService.getFileAttach(id);
			if (attach != null) {
				File file = new File(attach.getPath());
				if(file.exists() && file.canWrite()) {
					file.delete();
				}
				fileAttachService.deleteFile(id);
			}
			json.setType(ResultType.SUCCESS);
			return json;
		}
		return null;
	}

	private String getUploadFolder() {
		WebApplicationContext servletContext = ContextLoader.getCurrentWebApplicationContext();

		String root = servletContext.getServletContext().getRealPath("/");
		String path = root + File.separator + "file" + File.separator
				+ new SimpleDateFormat("yyyyMM").format(new Date());
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return path;
	}
}
