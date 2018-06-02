package org.plum.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.plum.tools.pagination.Pagination;
import org.plum.tools.pagination.entity.Page;
import org.plum.tools.pagination.entity.PageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PaginationHandlerMethodInterceptor extends HandlerInterceptorAdapter {

	private final static Logger log = LoggerFactory.getLogger(PaginationHandlerMethodInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("PaginationHandlerMethodInterceptor st" + "art...");
		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// paging
			Pagination pagination = handlerMethod.getMethodAnnotation(Pagination.class);
			if (pagination != null) {
				String size = request.getParameter(Pagination.PAGE_SIZE_NAME);
				String start = request.getParameter(Pagination.Page_START);
				String no = request.getParameter(Pagination.Page_DRAW);
				int offset = StringUtils.isEmpty(start) ? Page.DEFAULT_NO : Integer.parseInt(start);
				int pageSize = StringUtils.isEmpty(size) ? Page.DEFAULT_SZIE : Integer.parseInt(size);
				int draw = StringUtils.isNumeric(no) ? Integer.parseInt(no) : Page.DEFAULT_NO;
				Page page = new Page(draw, offset, pageSize);
				PageContext.set(page);

				if (log.isDebugEnabled()) {
					log.debug("Find the Pagination annotation and setup page value.");
				}
			}
		}
		return true;
	}
}
