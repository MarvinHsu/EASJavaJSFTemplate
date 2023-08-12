package com.hsuforum.easjavatemplate.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.easjavatemplate.entity.primary.Master;
import com.hsuforum.easjavatemplate.web.vo.MasterVo;
/**
 * Transfer Master to MasterVo wrapper
 * @author Marvin
 */
public class MasterVoWrapper extends VoWrapperImpl<Master, java.lang.String>{
	
	private static final long serialVersionUID = 1L;
	

	@Override
	public MasterVo wrap(Master entity) {
		return new MasterVo(entity);
	}
}