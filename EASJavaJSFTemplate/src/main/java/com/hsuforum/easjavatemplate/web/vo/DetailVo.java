package com.hsuforum.easjavatemplate.web.vo;


import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easjavatemplate.entity.primary.Detail;


/**
 * Detail's Value Object Implement
 * @author Marvin
 */
public class DetailVo extends ValueObjectImpl<Detail, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		
	private String selectMasterId;

	/**
	 * Constructor
	 * 
	 */
	public DetailVo() {
		super(new Detail());
	}

	/**
	 * Constructor
	 * @param entity
	 */
	public DetailVo (Detail entity) {
		super(entity);
	}


	

	/**
	 * Get selected Detail's id
	 * 
	 * @return selectMasterId
	 */
	public String getSelectMasterId() {
		if (this.selectMasterId == null && this.getEntity().getMaster() != null) {
			this.selectMasterId = this.getEntity().getMaster().getId().toString();
		}
		return this.selectMasterId;
		
	}

	/**
	 * Set selected Detail's id
	 * @param selectDetailId
	 */
	public void setSelectMasterId(String selectMasterId) {
		
		this.selectMasterId = selectMasterId;
	}
	



}
