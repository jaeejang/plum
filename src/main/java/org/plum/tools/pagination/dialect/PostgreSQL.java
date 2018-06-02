package org.plum.tools.pagination.dialect;

public class PostgreSQL implements Dialect {

	@Override
	public String generateLimitSQL(String sql, int offset, int limit) {
		return sql + " limit " + limit + " offset " + offset;
	}

	@Override
	public String generateCountSQL(String sql) {
		return "select count(1) from (" + sql + ") as my_count";
	}

}
