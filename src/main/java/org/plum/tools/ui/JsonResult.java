package org.plum.tools.ui;

public class JsonResult extends JsonModel {

	private static final long serialVersionUID = 1L;
	
	private  ResultType type;
	
	private JsonResult(ResultType type) {
		super();
		this.setType(type);
	}
	
	public JsonResult() {
		super();
		this.type = ResultType.INFO;
	}
	

	public static JsonResult createInstance() {
		return new JsonResult();
	}

	public static JsonResult createInstance(ResultType type) {
		return new JsonResult(type);
	}
	
	public void setType(ResultType type) {
		this.type = type;
		if(this.containsAttribute("type")) {
			this.replace("type", type.getType());
			this.replace("name", type.getName());
		}
		else {
			this.addAttribute("type", type.getType());
			this.addAttribute("name",type.getName());
		}
	}

	public ResultType getType() {
		return type;
	}	
	
}
