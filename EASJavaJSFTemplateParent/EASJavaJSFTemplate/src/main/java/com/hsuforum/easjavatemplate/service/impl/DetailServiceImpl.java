package com.hsuforum.easjavatemplate.service.impl;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easjavatemplate.dao.DetailDao;
import com.hsuforum.easjavatemplate.entity.Detail;
import com.hsuforum.easjavatemplate.service.DetailService;

/**
 * Detail service implement
 * @author Marvin
 *
 */
@Service("detailService")
public class DetailServiceImpl extends BaseServiceImpl<Detail, java.lang.String, DetailDao>
	implements DetailService {

	private static final long serialVersionUID = 5726532263879391173L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(DetailServiceImpl.class);

	@Autowired
	private DetailDao dao;

 	/**
	 * Get Data Access Object
	 *
	 * @return detailDao
	 */
	public DetailDao getDao() {
		return this.dao;
	}

	/**
	 * Set Data Access Object
	 *
	 * @param baseDao
	 */
	public void setDao(final DetailDao baseDao) {
		this.dao = baseDao;
	}
 
		


	
	/**
	 * @see com.hsuforum.easportalm.service.DetailService#findAllFetchRelation()
	 */
	public List<Detail> findAllFetchRelation(){
		
		return this.getDao().findAllFetchRelation();
	}



}