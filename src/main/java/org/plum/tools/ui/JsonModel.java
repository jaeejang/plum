package org.plum.tools.ui;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.Conventions;

@SuppressWarnings("serial")
public class JsonModel extends LinkedHashMap<String, Object>{
	
	public JsonModel(){
		super();
	}
	
	
	public JsonModel addAttribute(String attributeName, Object attributeValue) {
		put(attributeName, attributeValue);
		return this;
	}
	

	public boolean containsAttribute(String attributeName) {
		return containsKey(attributeName);
	}
	

	public JsonModel mergeAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			for (Map.Entry<String, ?> entry : attributes.entrySet()) {
				String key = entry.getKey();
				if (!containsKey(key)) {
					put(key, entry.getValue());
				}
			}
		}
		return this;
	}
	

	public JsonModel addAttribute(Object attributeValue) {
		if (attributeValue instanceof Collection && ((Collection<?>) attributeValue).isEmpty()) {
			return this;
		}
		return addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
	}
}
