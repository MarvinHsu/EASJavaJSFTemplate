package com.hsuforum.easjavatemplate.web.jsf.managed.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easjavatemplate.entity.Detail;
import com.hsuforum.easjavatemplate.entity.Master;
import com.hsuforum.easjavatemplate.service.DetailJpaService;
import com.hsuforum.easjavatemplate.service.DetailService;
import com.hsuforum.easjavatemplate.service.MasterService;
import com.hsuforum.easjavatemplate.web.util.SelectHelper;
import com.hsuforum.easjavatemplate.web.vo.DetailVo;
import com.hsuforum.easjavatemplate.web.vowrapper.DetailVoWrapper;

/**
 * Detail managedBean
 * 
 * @author Marvin
 *
 */
@Component
@SessionScope
public class DetailManagedBean
		extends TemplatePrimeDataTableManagedBean<Detail, String, DetailService, DetailJpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real
	// page to return in detail page
	private String mode;

	// Main serive in managedBean
	@Autowired
	private DetailService service;
	@Autowired
	private DetailJpaService jpaService;

	// MasterService
	@Autowired
	private MasterService masterService;
	private List<SelectItem> masterList;

	/**
	 * Constructor
	 */
	public DetailManagedBean() {

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
		this.setVoWrapper(new DetailVoWrapper());

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
		Detail object = new Detail();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		this.setMode("Create");
	}

	/**
	 * If you need to process updating data after press create or update button, you
	 * need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Detail, java.lang.String> updatingData) {

		// Set drop down list for many-to-one
		if (this.getUpdatingData().getEntity().getMaster() != null) {
			this.getUpdatingData().setSelectMasterId(this.getUpdatingData().getEntity().getMaster().getId().toString());

		}

		this.setMode("Update");
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
	public DetailVo getUpdatingData() {
		return (DetailVo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Detail, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public DetailService getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(DetailService service) {
		this.service = service;
	}

	public DetailJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(DetailJpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * Get drop down list select item in edit page
	 * 
	 * @return
	 */
	public List<SelectItem> getMasterList() {

		if (this.masterList == null) {
			this.masterList = new ArrayList<SelectItem>();
			// First SelectItem default is SelectHelper.EMPTY_SELECTITEM
			this.masterList.add(SelectHelper.EMPTY_SELECTITEM);
			for (Master master : getMasterService().findAll()) {
				SelectItem item = new SelectItem();

				/**
				 * Because entity may not have name property, so you may modify setLabel()
				 *
				 */

				item.setValue(master.getId().toString());
				item.setLabel(master.getName());
				this.masterList.add(item);

			}
		}
		return masterList;
	}

	/**
	 * Get MasterService
	 * 
	 * @return MasterService
	 */
	public MasterService getMasterService() {
		return this.masterService;
	}

	/**
	 * Set MasterService
	 * 
	 * @param MasterService
	 */
	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

	/**
	 * Setup Master
	 */
	private void setupMaster() {
		// Because select id may be null or empty string, so you need to check
		if ((this.getUpdatingData().getSelectMasterId() != null)
				&& (this.getUpdatingData().getSelectMasterId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setMaster(getMasterService().findByPK(this.getUpdatingData().getSelectMasterId()));
		} else {
			this.getUpdatingData().getEntity().setMaster(null);
		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupMaster();

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
	protected List<Detail> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
