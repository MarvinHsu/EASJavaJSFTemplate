package com.hsuforum.easjavatemplate.web.jsf.managed.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean;
import com.hsuforum.common.web.jsf.utils.JSFUtils;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easjavatemplate.entity.Master;
import com.hsuforum.easjavatemplate.service.MasterJpaService;
import com.hsuforum.easjavatemplate.service.MasterService;
import com.hsuforum.easjavatemplate.web.vo.MasterVo;
import com.hsuforum.easjavatemplate.web.vowrapper.MasterVoWrapper;

/**
 * Master managedBean
 * 
 * @author Marvin
 *
 */
@ManagedBean
@SessionScoped
public class MasterManagedBean
		extends TemplatePrimeJpaDataTableManagedBean<Master, java.lang.String, MasterService, MasterJpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real
	// page to return in detail page
	private String mode;

	// Main serive in managedBean
	@ManagedProperty(value = "#{masterService}")
	private MasterService service;
	@ManagedProperty(value = "#{masterJpaService}")
	private MasterJpaService jpaService;

	/**
	 * Constructor
	 */
	public MasterManagedBean() {

		super();

	}

	/**
	 * Init config
	 */
	@PostConstruct
	public void init() {
		// Set show data in read page
		this.setInitShowListData(true);
		// Init find criteria
		this.initFindCriteriaMap();
		// Set vo wrapper
		this.setVoWrapper(new MasterVoWrapper());

	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Init create object
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		Master object = new Master();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");
		// Because has master-detail relationship, so remove managedbean in session
		JSFUtils.getFacesContext().getExternalContext().getSessionMap().remove("master_DetailManagedBean");
	}

	/**
	 * If you need to process updating data after press create or update button, you
	 * need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Master, java.lang.String> updatingData) {

		this.setMode("Update");
		// Because has master-detail relationship, so remove managedbean in session
		JSFUtils.getFacesContext().getExternalContext().getSessionMap().remove("master_DetailManagedBean");
	}

	/**
	 * Init find criteria map, find oper map and find sort map
	 *
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {

		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();

		findCriteriaMap.put("name", null);
		findCriteriaMap.put("code", null);

		this.setFindCriteriaMap(findCriteriaMap);
		// Set operation
		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		this.setFindOperMap(findOperMap);

		// Set sort
		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("name", "DESC");
		findSortMap.put("code", "DESC");
		this.setFindSortMap(findSortMap);
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getUpdatingData()
	 *
	 */
	@Override
	public MasterVo getUpdatingData() {
		return (MasterVo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Master, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public MasterService getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(MasterService service) {
		this.service = service;
	}

	/**
	 * @return the jpaService
	 */
	public MasterJpaService getJpaService() {
		return jpaService;
	}

	/**
	 * @param jpaService the jpaService to set
	 */
	public void setJpaService(MasterJpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {

	}

	/**
	 * If entity has many-to-one or many-to-many relation then Code Generator will
	 * make this method for modifying. You can modify it for your need Method. The
	 * main function is in read page fetch all relational date to avoid update page
	 * occur error.
	 * 
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Master> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
