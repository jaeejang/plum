package org.plum.tools.pagination.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.session.RowBounds;

public class Page extends RowBounds implements Serializable {

	private static final long serialVersionUID = -3805529090535665155L;
	public final static int DEFAULT_SIZE = 20;
	public final static int DEFAULT_NO = 0;
	private int totalRows;
	public final static Page DEFAULT = new Page();
	private int page_no;
	private int offset;
	private int pageSize;
	

	private Page() {
		super(0, DEFAULT_SIZE);
		this.totalRows = NO_ROW_LIMIT;
	}

	public Page( int page_no, int pageSize, int totalRows) {
		super((page_no -1)*pageSize , pageSize);
		this.offset = (page_no -1)*pageSize;
		this.page_no = page_no;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage_no() {
		return page_no;
	}

	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public boolean isFirstPage() {
		return offset <= 0;
	}

	public boolean isLastPage() {
		return offset + pageSize >= totalRows;
	}

	public boolean hasPreviousPage() {
		return offset > 0;
	}

	public boolean hasNextPage() {
		return offset + pageSize < totalRows;
	}


	public int getTotalPages() {
		if (totalRows <= 0 || pageSize <= 0) {
			return 0;
		}

		int count = totalRows / pageSize;
		if (totalRows % pageSize > 0) {
			count++;
		}
		return count;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
