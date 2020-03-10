package com.laptrinhjavaweb.repository.custom.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryImpl extends CommonRepository implements BuildingRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BuildingEntity> findByBuilder(Map<String, Object> params, BuildingSearchBuilder builder) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM building A");
		if (builder.getStaffId() != null && builder.getStaffId() > 0) {
			sqlBuilder.append(" INNER JOIN assignedbuilding AB ON A.id = AB.buildingid");
		}
		sqlBuilder.append(" WHERE 1 = 1");
		sqlBuilder = createSQLFindAllCommon(sqlBuilder, params);
		sqlBuilder = createSQLSpecial(sqlBuilder, builder);
		Query query = entityManager.createNativeQuery(sqlBuilder.toString(), BuildingEntity.class);
		return query.getResultList();
	}
	private StringBuilder createSQLSpecial(StringBuilder sqlBuilder, BuildingSearchBuilder builder) {
		if (StringUtils.isNotBlank(builder.getRentAreaFrom()) || StringUtils.isNotBlank(builder.getRentAreaTo())) {
			sqlBuilder.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE (A.id = ra.buildingid");
			if (StringUtils.isNotBlank(builder.getRentAreaFrom())) {
				sqlBuilder.append(" AND ra.value >= ").append(builder.getRentAreaFrom());
			}
			if (StringUtils.isNotBlank(builder.getRentAreaTo())) {
				sqlBuilder.append(" AND ra.value <= ").append(builder.getRentAreaTo());
			}
			sqlBuilder.append("))");
		}
		String[] types = builder.getTypes();
		if (types.length > 0) {
			sqlBuilder.append(" AND (");
			String sqlType = Arrays.stream(types).map(item -> "A.type LIKE '%"+item+"%'")
					.collect(Collectors.joining(" OR "));
			sqlBuilder.append(sqlType);
			sqlBuilder.append(")");
		}
		if (StringUtils.isNotBlank(builder.getRentCostFrom())) {
			sqlBuilder.append(" AND A.rentcost >= ").append(builder.getRentCostFrom());
		}
		if (StringUtils.isNotBlank(builder.getRentCostTo())) {
			sqlBuilder.append(" AND A.rentcost <= ").append(builder.getRentCostTo());
		}
		if (builder.getStaffId() != null && builder.getStaffId() > 0) {
			sqlBuilder.append(" AND AB.staffid =").append(builder.getStaffId());
		}
		return sqlBuilder;
	}
	
}
