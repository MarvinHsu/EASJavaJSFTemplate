package com.hsuforum.easjavatemplate.service;


import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easjavatemplate.entity.Master;

/**
 * Master service interface
 * @author Marvin
 *
 */
public interface MasterService extends BaseService<Master, java.lang.String> {



	/**
	 * If entity has many-to-one or many-to-many relation
	 * then Code Generator will make this interface for modifying.
	 * You can modify it for your need Method.
	 * The main function is in read page fetch all 
	 * relational date to avoid update page occur error.
	 * @return List<Master>
	 */
	List<Master> findAllFetchRelation();



}
