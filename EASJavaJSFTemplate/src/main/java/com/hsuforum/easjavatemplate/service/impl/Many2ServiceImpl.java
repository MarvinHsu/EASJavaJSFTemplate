package com.hsuforum.easjavatemplate.service.impl;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easjavatemplate.dao.primary.Many2Dao;
import com.hsuforum.easjavatemplate.entity.primary.Many2;
import com.hsuforum.easjavatemplate.service.Many2Service;

/**
 * Many2 service implement
 * @author Marvin
 *
 */
@Service("many2Service")
public class Many2ServiceImpl extends BaseServiceImpl<Many2, java.lang.String, Many2Dao>
	implements Many2Service {

	private static final long serialVersionUID = 8344623155352715774L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(Many2ServiceImpl.class);

	@Autowired
	private Many2Dao dao;

 	/**
	 * Get Data Access Object
	 *
	 * @return many2Dao
	 */
	public Many2Dao getDao() {
		return this.dao;
	}

	/**
	 * Set Data Access Object
	 *
	 * @param baseDao
	 */
	public void setDao(final Many2Dao baseDao) {
		this.dao = baseDao;
	}
 

 

	
	/**
	 * @see com.hsuforum.easportalm.service.Many2Service#findAllFetchRelation()
	 */
	public List<Many2> findAllFetchRelation(){
		
		return this.getDao().findAllFetchRelation();
	}



}