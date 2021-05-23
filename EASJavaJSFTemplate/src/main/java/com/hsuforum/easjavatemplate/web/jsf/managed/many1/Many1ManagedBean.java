package com.hsuforum.easjavatemplate.web.jsf.managed.many1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easjavatemplate.entity.Many1;
import com.hsuforum.easjavatemplate.entity.Many2;
import com.hsuforum.easjavatemplate.service.Many1JpaService;
import com.hsuforum.easjavatemplate.service.Many1Service;
import com.hsuforum.easjavatemplate.service.Many2Service;
import com.hsuforum.easjavatemplate.web.vo.Many1Vo;
import com.hsuforum.easjavatemplate.web.vowrapper.Many1VoWrapper;

/**
 * Many1 managedBean
 * 
 * @author Marvin
 *
 */
@Component
@SessionScope
public class Many1ManagedBean
		extends TemplatePrimeDataTableManagedBean<Many1, java.lang.String, Many1Service, Many1JpaService> {

	private static final long serialVersionUID = 1L;

	// Create or update status, create is Create, update is Update for finding real
	// page to return in detail page
	private String mode;

	// Main serive in managedBean
	@Autowired
	private Many1Service service;
	@Autowired
	private Many1JpaService jpaService;

	// private List<SelectItem> many2List = null;
	@Autowired
	private Many2Service many2Service;

	/**
	 * Constructor
	 */
	public Many1ManagedBean() {

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
		this.setVoWrapper(new Many1VoWrapper());

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
		Many1 object = new Many1();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));

		// When page has many-to-many check box, system will call his.getMany2List() and
		// inject return value to this.updatingData
		this.getUpdatingData().setMany2List(this.getMany2List());
		this.setMode("Create");
	}

	/**
	 * If you need to process updating data after press create or update button, you
	 * need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Many1, java.lang.String> updatingData) {

		// Set check box for many-to-many)
		this.getUpdatingData().setMany2List(this.getMany2List());
		Set<Many2> many2List = this.getUpdatingData().getEntity().getMany2s();
		if (many2List != null && many2List.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getMany2List().size(); i++) {

				Many2 many2 = (Many2) this.getUpdatingData().getMany2List().get(i);
				;
				if (many2List.contains(many2)) {
					// If current many2 already relate entity, add into selected list
					this.getUpdatingData().getMany2SelectedRowKeys().add(many2);
				}
			}
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
	public Many1Vo getUpdatingData() {
		return (Many1Vo) super.getUpdatingData();
	}

	/**
	 * Set UpdatingData
	 * 
	 */
	@Override
	public void setUpdatingData(ValueObject<Many1, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	/**
	 * Get service
	 */
	@Override
	public Many1Service getService() {

		return this.service;
	}

	/**
	 * Set service
	 * 
	 * @param service
	 */
	@Override
	public void setService(Many1Service service) {
		this.service = service;
	}

	/**
	 * @return the jpaService
	 */
	public Many1JpaService getJpaService() {
		return jpaService;
	}

	/**
	 * @param jpaService the jpaService to set
	 */
	public void setJpaService(Many1JpaService jpaService) {
		this.jpaService = jpaService;
	}

	/**
	 * manyToMany for Many2 return Many2 list select item for many-to-many check box
	 * 
	 * @return List<Many2>
	 */
	public List<Many2> getMany2List() {

		List<Many2> manyBoList = new ArrayList<Many2>();

		for (Many2 manyBo : getMany2Service().findAllFetchRelation()) {
			manyBoList.add(manyBo);

		}
		return manyBoList;
	}

	/**
	 * Get Many2Service
	 * 
	 * @return Many2Service
	 */
	public Many2Service getMany2Service() {
		return this.many2Service;
	}

	/**
	 * Set Many2Service
	 * 
	 * @param Many2Service
	 */
	public void setMany2Service(Many2Service many2Service) {
		this.many2Service = many2Service;
	}

	// manyToMany for Many2 end

	/**
	 * Setup Many1 and Many2 relation
	 *
	 */
	private void setupMany2() {

		if (this.getUpdatingData().getMany2List() != null) {
			List<Many2> many2SelectedRowKeys = this.getUpdatingData().getMany2SelectedRowKeys();
			this.getUpdatingData().getEntity().clearMany2s();
			// Get selected item from many2sSelectedRowKey
			Iterator<Many2> many2SelectedRowKeyIterator = many2SelectedRowKeys.iterator();

			while (many2SelectedRowKeyIterator.hasNext()) {
				Many2 rowKey = many2SelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addMany2(rowKey);
			}
		}
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {

		this.setupMany2();
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
	protected List<Many1> findAllData() {
		return this.getService().findAllFetchRelation();
	}

}
