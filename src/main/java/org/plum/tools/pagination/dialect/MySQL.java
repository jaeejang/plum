package org.plum.tools.pagination.dialect;

public class MySQL implements Dialect {

	@Override
	public String generateLimitSQL(String sql, int offset, int limit) {
		if(limit == -1) {
			limit = Integer.MAX_VALUE;
		}
		return sql + " limit " + offset + ", " + limit;
	}

	@Override
	public String generateCountSQL(String sql) {
		return "select count(1) from (" + sql + ") as my_count";
	}

}
