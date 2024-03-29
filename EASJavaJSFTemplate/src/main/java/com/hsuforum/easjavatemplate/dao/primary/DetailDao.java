package com.hsuforum.easjavatemplate.dao.primary;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easjavatemplate.entity.primary.Detail;

/**
 * Detail data access interface
 * @author Marvin
 *
 */
public interface DetailDao extends BaseDao<Detail, java.lang.String> {

	

	/**
	 * If entity has many-to-one or many-to-many relation
	 * then Code Generator will make this interface for modifying.
	 * You can modify it for your need Method.
	 * The main function is in read page fetch all 
	 * relational date to avoid update page occur error.
	 * @return List<Detail>
	 */
	List<Detail> findAllFetchRelation();

}
