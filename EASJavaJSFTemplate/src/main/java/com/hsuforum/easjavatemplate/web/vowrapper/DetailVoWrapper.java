package com.hsuforum.easjavatemplate.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easjavatemplate.entity.primary.Detail;
import com.hsuforum.easjavatemplate.web.vo.DetailVo;
/**
 * Transfer Detail to DetailVo wrapper
 * @author Marvin
 */
public class DetailVoWrapper extends VoWrapperImpl<Detail, java.lang.String>{
	
	private static final long serialVersionUID = 1L;
	

	@Override
	public DetailVo wrap(Detail entity) {
		return new DetailVo(entity);
	}
}