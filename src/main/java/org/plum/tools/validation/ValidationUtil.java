package org.plum.tools.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

public class ValidationUtil {
	public static Map<String, String> hashErrors(List<FieldError> errors) {
		if (errors != null && errors.size() > 0) {
			Map<String,String> vars = new HashMap<>();
			for (FieldError error : errors) {
				vars.put(error.getField(), error.getDefaultMessage());
			}
			return vars;
		}
		else
			return null;
	}
}
