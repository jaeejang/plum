package org.plum.tools.pagination.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.session.RowBounds;

public class Page extends RowBounds implements Serializable {

	private static final long serialVersionUID = -3805529090535665155L;
	public final static int DEFAULT_SZIE = 20;
	public final static int DEFAULT_NO = 0;
	private int totalRows;
	public final static Page DEFAULT = new Page();
	private int draw = 0;
	private int start;
	private int pageSize;
	
	

	private Page() {
		super(0, DEFAULT_SZIE);
		this.totalRows = NO_ROW_LIMIT;
	}

	public Page(int draw, int offset, int pageSize) {
		super(offset, pageSize);
		this.draw = draw;
		this.start = offset;
		this.pageSize = pageSize;
		this.totalRows = NO_ROW_LIMIT;
	}

	public Page(int draw, int offset, int pageSize, int totalRows) {
		super(offset, pageSize);
		this.draw = draw;
		this.start = offset;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public boolean isFirstPage() {
		return start <= 0;
	}

	public boolean isLastPage() {
		return start + pageSize >= totalRows;
	}

	public boolean hasPreviousPage() {
		return start > 0;
	}

	public boolean hasNextPage() {
		return start + pageSize < totalRows;
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

	public int getDraw() {
		return draw;
	}
}
