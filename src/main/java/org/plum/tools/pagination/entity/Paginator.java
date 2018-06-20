package org.plum.tools.pagination.entity;

import java.util.List;

public class Paginator {

	/*
	 * total pages for the query
	 */
	private int total;
	/*
	 * current page of the query
	 */
	private int page;
	/*
	 * total number of records for the query
	 */
	private int records;
	
	/*
	 * an array that contains the actual data
	 */
	private List<?> rows;
	
	/*
	 * the unique id of the row
	 */
	private String id;
	
	/*
	 * an array that contains the data for a row
	 */
	private Object cell;

	public Paginator(List<?> rows) {
		this.rows = rows;
		Page page = PageContext.get();
		this.records = page.getTotalRows();
		this.total = this.records / page.getPageSize() + 1;
		this.page = page.getPage_no();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}

	public int getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public int getRecords() {
		return records;
	}

	public List<?> getRows() {
		return rows;
	}
	
	

}
