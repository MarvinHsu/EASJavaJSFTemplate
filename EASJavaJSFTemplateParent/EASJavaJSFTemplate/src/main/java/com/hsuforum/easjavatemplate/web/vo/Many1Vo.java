package com.hsuforum.easjavatemplate.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easjavatemplate.entity.Many1;
import com.hsuforum.easjavatemplate.entity.Many2;

/**
 * Many1's Value Object Implement
 * @author Marvin
 */
public class Many1Vo extends ValueObjectImpl<Many1, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		
	private List<Many2> many2List = null;
	private List<Many2> many2SelectedRowKeys ;	

	/**
	 * Constructor
	 * 
	 */
	public Many1Vo() {
		super(new Many1());
	}

	/**
	 * Constructor
	 * @param entity
	 */
	public Many1Vo (Many1 entity) {
		super(entity);
	}


	


	
	/**
	 * Get Many2 list
	 * @return List<Many2>
	 */
	public List<Many2> getMany2List() {
		return this.many2List;
	}
	
	/**
	 * Set Many2 list
	 * @param many2List
	 */
	public void setMany2List(List<Many2> many2List) {
		this.many2List = many2List;
	}
	
	/**
	 * Get checked Many2 Collection
	 * @return List<Many2>
	 */
	public List<Many2> getMany2SelectedRowKeys() {
		if(this.many2SelectedRowKeys==null){
			this.many2SelectedRowKeys = new ArrayList<Many2>();
		}
		return this.many2SelectedRowKeys;
	}
	
	/**
	 * Set checked Many2 collection
	 * @param Many2SelectedRowKeys
	 */
	public void setMany2SelectedRowKeys(List<Many2> many2SelectedRowKeys) {
		this.many2SelectedRowKeys = many2SelectedRowKeys;
	}
	
	
	



}
