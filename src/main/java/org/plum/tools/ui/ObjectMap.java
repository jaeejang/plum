package org.plum.tools.ui;

public class ObjectMap {

	private String	label;
	
	private String name;
	
	private String dict_type;

	public ObjectMap(String label, String name) {
		this.label = label;
		this.name = name;
	}
	
	public ObjectMap(String label, String name, String dict_type) {
		this.label = label;
		this.name = name;
		this.dict_type = dict_type;
	}
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDict_type() {
		return dict_type;
	}

	public void setDict_type(String dict_type) {
		this.dict_type = dict_type;
	}
	
	
}
