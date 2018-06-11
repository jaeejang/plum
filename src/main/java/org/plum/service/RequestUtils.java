package org.plum.service;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;

public class RequestUtils {

	public static <T> T getQueryParm(Map<String, String[]> params, Class<T> type, String paramName) {
		Object returnValue = null;
		if (params.containsKey(paramName) && params.get(paramName).length > 0) {
			String paramValue = params.get(paramName)[0];
			if (type != String.class) {
				if (NumberUtils.isCreatable(paramValue)) {
					if (type == int.class || type == Integer.class) {
						returnValue = Integer.parseInt(paramValue);
					} else if (type == double.class || type == Double.class)
						returnValue = Double.parseDouble(paramValue);
				}
			} else {
				returnValue = paramValue;
			}
		}
		return type.cast(returnValue);
	}	
}
