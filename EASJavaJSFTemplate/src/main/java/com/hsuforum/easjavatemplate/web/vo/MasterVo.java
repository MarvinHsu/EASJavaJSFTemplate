package com.hsuforum.easjavatemplate.web.vo;


import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easjavatemplate.entity.Master;


/**
 * Master's Value Object Implement
 * @author Marvin
 */
public class MasterVo extends ValueObjectImpl<Master, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		

	/**
	 * Constructor
	 * 
	 */
	public MasterVo() {
		super(new Master());
	}

	/**
	 * Constructor
	 * @param entity
	 */
	public MasterVo (Master entity) {
		super(entity);
	}


	



}
