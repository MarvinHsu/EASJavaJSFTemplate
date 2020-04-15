package com.hsuforum.easjavatemplate.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easjavatemplate.entity.Many1;

/**
 * Many1 data access interface
 * @author Marvin
 *
 */
public interface Many1Dao extends BaseDao<Many1, java.lang.String> {


	/**
	 * If entity has many-to-one or many-to-many relation
	 * then Code Generator will make this interface for modifying.
	 * You can modify it for your need Method.
	 * The main function is in read page fetch all 
	 * relational date to avoid update page occur error.
	 * @return List<Many1>
	 */
	List<Many1> findAllFetchRelation();

}
