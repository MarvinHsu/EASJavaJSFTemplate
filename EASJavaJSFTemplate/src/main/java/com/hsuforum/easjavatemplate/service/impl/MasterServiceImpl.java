package com.hsuforum.easjavatemplate.service.impl;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easjavatemplate.dao.primary.MasterDao;
import com.hsuforum.easjavatemplate.entity.primary.Master;
import com.hsuforum.easjavatemplate.service.MasterService;

/**
 * Master service implement
 * @author Marvin
 *
 */
@Service("masterService")
public class MasterServiceImpl extends BaseServiceImpl<Master, java.lang.String, MasterDao>
	implements MasterService {

	private static final long serialVersionUID = 8011529218173860439L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(MasterServiceImpl.class);

	@Autowired
	private MasterDao dao;

 	/**
	 * Get Data Access Object
	 *
	 * @return masterDao
	 */
	public MasterDao getDao() {
		return this.dao;
	}

	/**
	 * Set Data Access Object
	 *
	 * @param baseDao
	 */
	public void setDao(final MasterDao baseDao) {
		this.dao = baseDao;
	}
 


	
	/**
	 * @see com.hsuforum.easportalm.service.MasterService#findAllFetchRelation()
	 */
	public List<Master> findAllFetchRelation(){
		
		return this.getDao().findAllFetchRelation();
	}



}