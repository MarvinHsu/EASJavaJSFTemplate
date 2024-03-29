package com.hsuforum.easjavatemplate.service;


import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easjavatemplate.entity.primary.Many2;

/**
 * Many2 service interface
 * @author Marvin
 *
 */
public interface Many2Service extends BaseService<Many2, java.lang.String> {



	/**
	 * If entity has many-to-one or many-to-many relation
	 * then Code Generator will make this interface for modifying.
	 * You can modify it for your need Method.
	 * The main function is in read page fetch all 
	 * relational date to avoid update page occur error.
	 * @return List<Many2>
	 */
	List<Many2> findAllFetchRelation();



}
