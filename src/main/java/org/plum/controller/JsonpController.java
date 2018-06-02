package org.plum.controller;

import org.plum.initial.PlumContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
@RequestMapping("/jsonp")
public class JsonpController {
	@Autowired
	PlumContext context;
	
	@RequestMapping("/dict")  
    @ResponseBody
    public JSONPObject getDicts(String callbackparam){
		return new JSONPObject(callbackparam,  context.getDicts());   
	} 
}
