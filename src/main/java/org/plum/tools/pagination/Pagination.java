package org.plum.tools.pagination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pagination {

	/*
	 * DataTable 客户端传递值
	 */
	public final static String PAGE_NO_NAME = "page";
	public final static String Page_START = "start";
	public final static String PAGE_SIZE_NAME = "length";
	public final static String Page_DRAW = "draw";

	
	
	/*
	 * jqGrid 客户端传递值
	 * {page:“page”,rows:“rows”, sort:“sidx”, order:“sord”, search:“_search”, nd:“nd”, id:“id”, oper:“oper”, editoper:“edit”, addoper:“add”, deloper:“del”, subgridid:“id”, npage:null, totalrows:“totalrows”} 
	 */
	public final static String PAGE = "page";
	public final static String ROWS="rows";
	public final static String SORT="sidx";
	public final static String ORDER="sord";
	public final static String SEARCH="_search";
	public final static String OPERATE = "oper";
	public final static String ND = "nd";
	public final static String ADD_OPERATE="add";
	public final static String EDIT_OPERATE="edit";
	public final static String ID="id";
	public final static String DEL_OPERATE="del";
	public final static String TOTALROWS="totalrows";
	public final static String SUBGRID_ID="id";
	
}