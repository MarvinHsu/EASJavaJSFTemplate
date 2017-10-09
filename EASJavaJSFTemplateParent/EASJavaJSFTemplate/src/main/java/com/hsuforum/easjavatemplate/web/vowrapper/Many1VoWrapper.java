package com.hsuforum.easjavatemplate.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easjavatemplate.entity.Many1;
import com.hsuforum.easjavatemplate.web.vo.Many1Vo;
/**
 * Transfer Many1 to Many1Vo wrapper
 * @author Marvin
 */
public class Many1VoWrapper extends VoWrapperImpl<Many1, java.lang.String>{
	
	private static final long serialVersionUID = 1L;
	

	@Override
	public Many1Vo wrap(Many1 entity) {
		return new Many1Vo(entity);
	}
}