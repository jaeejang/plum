package org.plum.tools.pagination.entity;

public class Paginator {

	private int draw;
	private int offset;
	private int recordsTotal;
	private Object data;

	public Paginator(Object items) {
		this.data = items;
		Page page = PageContext.get();
		this.recordsTotal = page.getTotalRows();
		this.offset = page.getOffset();
		this.draw = page.getDraw() + 1;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int draw) {
		this.offset = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}
	
	public int getRecordsFiltered(){
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
