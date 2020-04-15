package com.hsuforum.easjavatemplate.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easjavatemplate.entity.Many2;
import com.hsuforum.easjavatemplate.web.vo.Many2Vo;
/**
 * Transfer Many2 to Many2Vo wrapper
 * @author Marvin
 */
public class Many2VoWrapper extends VoWrapperImpl<Many2, java.lang.String>{
	
	private static final long serialVersionUID = 1L;
	

	@Override
	public Many2Vo wrap(Many2 entity) {
		return new Many2Vo(entity);
	}
}