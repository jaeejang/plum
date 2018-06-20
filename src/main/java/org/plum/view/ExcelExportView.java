package org.plum.view;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import freemarker.template.SimpleDate;

import org.plum.controller.advice.AdviceController;
import org.plum.initial.PlumCache;
import org.plum.model.advice.Advice;
import org.plum.model.system.Dict;
import org.plum.tools.ui.ObjectMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelExportView extends AbstractXlsView {	

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String excelName =  URLEncoder.encode(model.get("name").toString(), "utf-8");
		response.setHeader("content-disposition", "attachment;filename=" + String.format("%s_%s%s", excelName,new SimpleDateFormat("yyyyMMdd").format(new Date()),".xls"));
		response.setContentType("application/ms-excel; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Sheet sheet = workbook.createSheet();

		 Row header = sheet.createRow(0);
		 List<ObjectMap>map = (List<ObjectMap>)(model.get("map"));
		 for (int i = 0; i < map.size(); i++) {
			 header.createCell(i).setCellValue(map.get(i).getLabel());
		}
		 List<Advice> advices = (List<Advice>) model.get("data");
		 int rowNum = 1;
		 for (Advice advice : advices) {
			 Row row = sheet.createRow(rowNum++);
			 for (int i = 0; i < map.size(); i++) {
				 String name = map.get(i).getName();
				 //TODO get value;
				 Field field = Advice.class.getDeclaredField(name);
				 field.setAccessible(true);
				 Object value = field.get(advice);

				 // 枚举值处理
				 String dict = map.get(i).getDict_type();
				 if(dict != null) {
					 Integer intValue = (Integer)value;
					 row.createCell(i).setCellValue(getDictName(dict,intValue));
				 }
				 
				 if(value instanceof  Integer) {
					 row.createCell(i).setCellValue((Integer)value);
				 }else if(value instanceof String) {
					 row.createCell(i).setCellValue((String)value);
				 }else if(value instanceof Date) {
					 row.createCell(i).setCellValue(new SimpleDateFormat("yyyyMMdd H24:mm:ss").format((Date)value));
				 }else {
					 log.warn("NO METHOD TO MAP %s AND %s",name, value==null?"":value.toString());
				 }
			}
		}		 
	}
	
	
	private String getDictName(String type,int code) {
		for (Dict dict : PlumCache.CacheDicts) {
			if(dict.getType().equals(type) && dict.getCode().equals(code)) {
				return dict.getName();
			}
		}
		return null;
	}

}
