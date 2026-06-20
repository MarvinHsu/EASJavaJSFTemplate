package com.hsuforum.easjavatemplate.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.easjavatemplate.entity.primary.Many1;
import com.hsuforum.easjavatemplate.entity.primary.Many2;

/**
 * Many2's Value Object Implement
 * @author Marvin
 */
public class Many2Vo extends ValueObjectImpl<Many2, java.lang.String>{
	
	private static final long serialVersionUID = 1L;		
	private List<Many1> many1List = null;
	private List<Many1> many1SelectedRowKeys ;	

	/**
	 * Constructor
	 * 
	 */
	public Many2Vo() {
		super(new Many2());
	}

	/**
	 * Constructor
	 * @param entity
	 */
	public Many2Vo (Many2 entity) {
		super(entity);
	}


	


	
	/**
	 * Get Many1 list
	 * @return List<Many1>
	 */
	public List<Many1> getMany1List() {
		return this.many1List;
	}
	
	/**
	 * Set Many1 list
	 * @param many1List
	 */
	public void setMany1List(List<Many1> many1List) {
		this.many1List = many1List;
	}
	
	/**
	 * Get checked Many1 Collection
	 * @return List<Many1>
	 */
	public List<Many1> getMany1SelectedRowKeys() {
		if(this.many1SelectedRowKeys==null){
			this.many1SelectedRowKeys = new ArrayList<Many1>();
		}
		return this.many1SelectedRowKeys;
	}
	
	/**
	 * Set checked Many1 collection
	 * @param Many1SelectedRowKeys
	 */
	public void setMany1SelectedRowKeys(List<Many1> many1SelectedRowKeys) {
		this.many1SelectedRowKeys = many1SelectedRowKeys;
	}
	
	
	



}
