package com.laptrinhjavaweb.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	/*HQL
	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findByBuildingId(Integer buildingId) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT u FROM UserEntity u JOIN u.buildings b");
		sqlBuilder.append(" WHERE 1 = 1");
		sqlBuilder.append(" AND b.id =:buildingId");
		Query query = entityManager.createQuery(sqlBuilder.toString());
		query.setParameter("buildingId", buildingId);
		return query.getResultList();
	}
	*/

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findByBuildingId(Integer buildingId) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM user u INNER JOIN assignedbuilding ab");
		sqlBuilder.append(" ON u.id = ab.staffid");
		sqlBuilder.append(" WHERE 1=1");
		sqlBuilder.append(" AND ab.buildingid =:buildingId");
		Query query = entityManager.createNativeQuery(sqlBuilder.toString(), UserEntity.class);
		query.setParameter("buildingId", buildingId);
		return query.getResultList();
	}
	
}
