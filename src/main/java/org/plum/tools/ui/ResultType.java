package org.plum.tools.ui;


public enum ResultType{

	
	ERROR("Error","错误"),
	SUCCESS ("Success","成功"),
	INFO ("Info","提示"),
	WARNING("Warning","警告");
	
	private String type;
	private String name;
	
	
	private ResultType(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return this.type.toLowerCase();
	}
}