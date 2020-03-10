package com.laptrinhjavaweb.repository.custom.impl;

import java.util.Map;

public class CommonRepository{
	
	protected StringBuilder createSQLFindAllCommon(StringBuilder sqlBuilder, Map<String, Object> params) {
		if (params != null && params.size() > 0) {
			String[] keys = new String[params.size()];
			Object[] values = new Object[params.size()];
			int index = 0;
			for (Map.Entry<String, Object> item : params.entrySet()) {
				keys[index] = item.getKey();
				values[index] = item.getValue();
				index++;
			}
			for (int i = 0; i < keys.length; i++) {
				if (values[i] instanceof String) {
					sqlBuilder.append(" AND A.").append(keys[i]);
					sqlBuilder.append(" LIKE '%").append(values[i]).append("%'");
				} else {
					sqlBuilder.append(" AND A.").append(keys[i]);
					sqlBuilder.append(" = ").append(values[i]);
				}
			}
		}
		return sqlBuilder;
	}
}
