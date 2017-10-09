package com.hsuforum.easjavatemplate.web.jsf.managed.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.jsf.utils.JSFMessageUtils;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.easjavatemplate.entity.Detail;
import com.hsuforum.easjavatemplate.service.DetailService;
import com.hsuforum.easjavatemplate.web.vo.DetailVo;
import com.hsuforum.easjavatemplate.web.vowrapper.DetailVoWrapper;


/**
 * Detail managedBean
 * @author Marvin
 *
 */
@ManagedBean
@SessionScoped
public class Master_DetailManagedBean extends TemplatePrimeDataTableManagedBean
	<Detail, java.lang.String, DetailService>{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{masterManagedBean}")
	private MasterManagedBean masterManagedBean;



	//Main serive in managedBean
	@ManagedProperty(value="#{detailService}")
    private DetailService service;



	/**
	 * Constructor
	 */
	public Master_DetailManagedBean() {
		
		super();

	}
	
	/**
	 * Init config
	 */
	@PostConstruct
	public void init() {
		//Set show data in read page
		this.setInitShowListData(true);
		//Init find criteria
		this.initFindCriteriaMap();
		//Set vo wrapper
		this.setVoWrapper(new DetailVoWrapper());
	
	}
	


	public MasterManagedBean getMasterManagedBean() {
		return this.masterManagedBean;
	}

	public void setMasterManagedBean(MasterManagedBean masterManagedBean) {
		this.masterManagedBean = masterManagedBean;
	}

	/**
	 * Init create object
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#initCreatingData()
	 *
	 */
	@Override
	protected void initCreatingData() {
		Detail object = new Detail();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		
	}
	
	/**
	 * If you need to process updating data after press create or update button, you need override this method
	 * 
	 */
	@Override
	protected void initUpdatingData(ValueObject<Detail, java.lang.String> updatingData) {
	

	}

    /**
	 * Init find criteria map, find oper map and find sort map
	 *
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
		
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		
									
					findCriteriaMap.put("name", null);
											
					findCriteriaMap.put("code", null);
					
		this.setFindCriteriaMap(findCriteriaMap);
		//Set operation
        Map<String, String> findOperMap = new HashMap<String, String>();    
    		findOperMap.put("name", "eq");		
			findOperMap.put("code", "eq");		
	        this.setFindOperMap(findOperMap);
        
        //設定排序
        Map<String, String> findSortMap = new HashMap<String, String>();
    		findSortMap.put("name", "DESC");		
			findSortMap.put("code", "DESC");		
	        this.setFindSortMap(findSortMap);
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#getUpdatingData()
	 *
	 */
	@Override
	public DetailVo getUpdatingData() {
		return (DetailVo)super.getUpdatingData();
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
     * @param service
     */
    @Override
    public void setService(DetailService service) {
        this.service = service;
    }




	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBean#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {

	}
	
	
	
	/**
	 * 
	 */
	@Override
	protected List<Detail> findAllData() {
	
		Set<Detail> set = this.getMasterManagedBean().getUpdatingData().getEntity().getDetails();
		List<Detail> list = new ArrayList<Detail>();
		if(set!=null && set.size()>0){
			for(Detail detail : set){
				list.add(detail);
			}
		}
		return list;
	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doCreateAction()
	 */
	@Override
	public String doCreateAction() {
        //call parent method
		super.doCreateAction();
        return "createDetail";
    }
    
	/**
     * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doCancelCreateAction()
     */
    @Override
    public String doCancelCreateAction() {
    	//call parent method
    	super.doCancelCreateAction();
        if(this.getMasterManagedBean().getMode().equals("Create")){
            return "readCreate";
        }else{
            return "readUpdate";
        }
    }
	
	/**
     * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doSaveCreateAction()
     */
    @Override
    public String doSaveCreateAction() {
        try {
            //setup updating data
            this.setupUpdatingData();
            
            //init collection
            if(this.getMasterManagedBean().getUpdatingData().getEntity().getDetails() == null){
            	this.getMasterManagedBean().getUpdatingData().getEntity().setDetails(new LinkedHashSet<Detail>());
            }
            
            //set create or update object in master managedbean
            this.getMasterManagedBean().getUpdatingData().getEntity().addDetail(this.getUpdatingData().getEntity());
            
            //refresh data
            this.doRefreshData();
            
            //return to create or update page of parent
            if(this.getMasterManagedBean().getMode().equals("Create")){
            	return "readCreate";
            }else{
            	return "readUpdate";
            }
            
        } catch (Exception ex) {
            JSFMessageUtils.showErrorMessage(ex.getMessage());
            return null;

        }
    }

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doUpdateAction()
	 */
	public String doUpdateAction() {
		//call parent method
		super.doUpdateAction();
		return "updateDetail";
	}

    /**
     * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doSaveUpdateAction()
     */
    @Override
    public String doSaveUpdateAction() {
        try {
            //setup updating data
            this.setupUpdatingData();
            
            //init collection
            if(this.getMasterManagedBean().getUpdatingData().getEntity().getDetails() == null){
            	this.getMasterManagedBean().getUpdatingData().getEntity().setDetails(new LinkedHashSet<Detail>());
            }
            
            //set create or update object in master managedbean
            this.getMasterManagedBean().getUpdatingData().getEntity().addDetail(this.getUpdatingData().getEntity());
            
            //refresh data
            this.doRefreshData();
            
            //return to create or update page of parent
            if(this.getMasterManagedBean().getMode().equals("Create")){
            	return "readCreate";
            }else{
            	return "readUpdate";
            }
         
        } catch (Exception ex) {
            JSFMessageUtils.showErrorMessage(ex.getMessage());
            return null;

        }
    }

    /**
     * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doCancelUpdateAction()
     */
    @Override
	public String doCancelUpdateAction() {
		//call parent method
		super.doCancelUpdateAction();
		if(this.getMasterManagedBean().getMode().equals("Create")){
            return "readCreate";
        }else{
            return "readUpdate";
        }
	}
	
	/**
     * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplateDataTableManagedBean#doDeleteOneAction()
     */
	@Override
	public String doDeleteAction() {
        try {
        	//set create or update object in master managedbean
            this.getMasterManagedBean().getUpdatingData().getEntity().removeDetail(this.getUpdatingData().getEntity());
            
            //refresh data
            this.doRefreshData();
            
            //return to create or update page of parent
            if(this.getMasterManagedBean().getMode().equals("Create")){
            	return "readCreate";
            }else{
            	return "readUpdate";
            }
        } catch (Exception ex) {
            JSFMessageUtils.showErrorMessage(ex.getMessage());
            return null;

        }
    }
}


