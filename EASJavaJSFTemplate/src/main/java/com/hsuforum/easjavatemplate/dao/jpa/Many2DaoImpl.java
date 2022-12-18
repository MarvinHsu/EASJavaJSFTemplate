package com.hsuforum.easjavatemplate.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.easjavatemplate.dao.Many2Dao;
import com.hsuforum.easjavatemplate.entity.Many2;

/**
 * Many2 data access implement
 * 
 * @author Marvin
 *
 */
@Repository("many2Dao")
public class Many2DaoImpl extends BaseDaoImpl<Many2, java.lang.String> implements Many2Dao {

	private static final long serialVersionUID = 2065349864889630169L;
	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;

	/**
	 * Get entityManager
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * Set entityManager
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @see com.hsuforum.easportalm.dao.Many2Dao#findAllFetchRelation()
	 */
	public List<Many2> findAllFetchRelation() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM Many2 entity	");
		queryString.append("LEFT JOIN FETCH entity.many1s ");
		queryString.append("ORDER BY entity.id	");

		List<Many2> list = this.find(queryString);

		return list;
	}

	@Override
	public List<Many2> findByCriteriaMap(final Map<String, ? extends Object> criteriaMap,
			final Map<String, String> operMap, final Map<String, String> sortMap) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM Many2 entity	");
		queryString.append("LEFT JOIN FETCH entity.many1s ");

		Map<String, Object> newCriteriaMap = new HashMap<String, Object>();

		boolean isTruncateWhereQueryString = false;
		boolean isTruncateSortQueryString = false;
		for (String criteriaKey : criteriaMap.keySet()) {

			if (null == criteriaMap.get(criteriaKey)) {
				continue;
			}
			if (criteriaMap.get(criteriaKey).toString().compareTo("") != 0) {
				if (!isTruncateWhereQueryString) {
					queryString.append(" WHERE ");
				}
				if (operMap != null && operMap.size() > 0) {
					if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("eq")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" = :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("ge")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" >= :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("gt")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" > :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("le")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" <= :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("lt")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" < :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("ne")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" >< :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("like")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" like :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else {
						queryString.append("entity.").append(criteriaKey.toString()).append(" = :")
								.append(criteriaKey.toString().replace(".", "_"));
					}
				} else {
					queryString.append("entity.").append(criteriaKey.toString()).append(" = :")
							.append(criteriaKey.toString().replace(".", "_"));
				}
				queryString.append(" AND ");

				newCriteriaMap.put(criteriaKey.replace(".", "_"), criteriaMap.get(criteriaKey));
				isTruncateWhereQueryString = true;
			}
		}

		if (isTruncateWhereQueryString) {
			int lastIndex = queryString.lastIndexOf("AND");
			queryString.delete(lastIndex, queryString.length());
		}

		if (sortMap != null) {
			for (String sortKey : sortMap.keySet()) {

				if (null == sortMap.get(sortKey)) {
					continue;
				}
				if (sortMap.get(sortKey).toString().compareTo("") != 0) {
					if (!isTruncateSortQueryString) {
						queryString.append(" ORDER BY ");

					}
					queryString.append("entity.").append(sortKey.toString()).append(" ").append(sortMap.get(sortKey));
					queryString.append(" , ");

					isTruncateSortQueryString = true;
				}
			}

			if (isTruncateSortQueryString) {
				int lastIndex = queryString.lastIndexOf(",");
				queryString.delete(lastIndex, queryString.length());
			}
		}
		return this.findByNamedParams(queryString, newCriteriaMap);
	}

}
