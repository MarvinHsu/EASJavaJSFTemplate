package com.hsuforum.easjavatemplate.service.impl;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easjavatemplate.dao.Many1Dao;
import com.hsuforum.easjavatemplate.entity.Many1;
import com.hsuforum.easjavatemplate.service.Many1Service;

/**
 * Many1 service implement
 * @author Marvin
 *
 */
@Service("many1Service")
public class Many1ServiceImpl extends BaseServiceImpl<Many1, java.lang.String, Many1Dao>
	implements Many1Service {

	private static final long serialVersionUID = 5832226624965941422L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(Many1ServiceImpl.class);

	@Autowired
	private Many1Dao dao;

 	/**
	 * Get Data Access Object
	 *
	 * @return many1Dao
	 */
	public Many1Dao getDao() {
		return this.dao;
	}

	/**
	 * Set Data Access Object
	 *
	 * @param baseDao
	 */
	public void setDao(final Many1Dao baseDao) {
		this.dao = baseDao;
	}
 

 

	
	/**
	 * @see com.hsuforum.easportalm.service.Many1Service#findAllFetchRelation()
	 */
	public List<Many1> findAllFetchRelation(){
		
		return this.getDao().findAllFetchRelation();
	}



}