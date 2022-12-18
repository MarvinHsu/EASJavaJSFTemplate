package com.hsuforum.easjavatemplate.web.jsf.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.easjavatemplate.DefaultSetting;
import com.hsuforum.easjavatemplate.security.util.AAUtils;
import com.hsuforum.easjavatemplate.ws.client.PortalClient;
import com.hsuforum.easjavatemplate.ws.vo.FunctionWSVO2;
import com.hsuforum.easjavatemplate.ws.vo.GroupFunctionWSVO;
import com.hsuforum.easjavatemplate.ws.vo.GroupWSVO;
import com.hsuforum.easjavatemplate.ws.vo.ModuleWSVO2;
import com.hsuforum.easjavatemplate.ws.vo.UserWSVO;

/**
 * Navigation menu managed bean
 */
@Component
@SessionScope
public class MenuManagedBean implements Serializable {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private static final long serialVersionUID = 7319288785728714429L;

	@Autowired
	private DefaultSetting defaultSetting;
	@Autowired
	private PortalClient portalClient;

	private UserWSVO userWSVO;
	private List<ModuleWSVO2> moduleWSVO2s;

	private Integer activeTab;

	public MenuManagedBean() {
		super();

	}

	@PostConstruct
	public void init() {
		// get user id
        //AttributePrincipal principal = (AttributePrincipal)JSFUtils.getHttpServletRequest().getUserPrincipal();        
       // Map attributes = principal.getAttributes();
        //String userId=(String)attributes.get("sAMAccountName");
        Object principal=AAUtils.getLoggedInUser();
        if(principal instanceof UserWSVO) {
			//this.userId = JSFUtils.getHttpServletRequest().getRemoteUser();
        	UserWSVO userWSVO=(UserWSVO)principal;
	        logger.info("login java_jsf_tempate userWSVO ="+userWSVO);
	        
			this.userWSVO = this.getPortalClient().findUserById(this.getDefaultSetting().getSystemId(), userWSVO.getAccount().toUpperCase());
			ModuleWSVO2[] moduleArray = this.getPortalClient().findModuleBySystem(this.getDefaultSetting().getSystemId());
	
			this.moduleWSVO2s = new ArrayList<ModuleWSVO2>();
			if (moduleArray != null) {
				for (int j = 0; j < moduleArray.length; j++) {
					this.moduleWSVO2s.add(moduleArray[j]);
				}
	
				if (this.moduleWSVO2s.size() > 0) {
					this.activeTab = 0;
				}
				if (this.userWSVO != null && userWSVO.getAuthorities() != null && this.moduleWSVO2s != null) {
					
					for (int i = 0; i < this.moduleWSVO2s.size(); i++) {
						if (this.moduleWSVO2s.get(i).getShowed() == true) {
							//use set to prevent duplication
							Set<FunctionWSVO2> functionWSVO2Set = new HashSet<FunctionWSVO2>();
							for (GrantedAuthority grantedAuthority : userWSVO.getAuthorities()) {

								for (GroupFunctionWSVO groupFunctionWSVO : ((GroupWSVO) grantedAuthority).getGroupFunctionWSVOs()) {

									if (groupFunctionWSVO.getFunctionWSVO().getModuleWSVO() != null && groupFunctionWSVO.getFunctionWSVO()
											.getModuleWSVO().getCode().equals(this.moduleWSVO2s.get(i).getCode())) {
										FunctionWSVO2[] functionWSVO2Iter = this.moduleWSVO2s.get(i).getFunctionWSVO2s();

										for(int j=0;j<functionWSVO2Iter.length;j++){
											FunctionWSVO2 functionWSVO2 = functionWSVO2Iter[j];
											if (groupFunctionWSVO.getFunctionWSVO().getCode().equals(functionWSVO2.getCode())
													&& functionWSVO2.getShowed() == true) {
												functionWSVO2Set.add(functionWSVO2);
											}
										}

									}

								}
							}
							List<FunctionWSVO2> functionWSVO2ArrayList= new ArrayList<FunctionWSVO2>();
							functionWSVO2ArrayList.addAll(functionWSVO2Set);

							Collections.sort(functionWSVO2ArrayList, new Comparator<FunctionWSVO2>() {
								public int compare(FunctionWSVO2 s1, FunctionWSVO2 s2) {

									return s1.getSequence().compareTo(s2.getSequence());

								}
							});
							FunctionWSVO2[] gunctionWSVOArray = new FunctionWSVO2[functionWSVO2ArrayList.size()];
							functionWSVO2ArrayList.toArray(gunctionWSVOArray);

							this.moduleWSVO2s.get(i).setFunctionWSVO2s(gunctionWSVOArray);
						}
						//if module haven't any function. hidden it
						if(this.moduleWSVO2s.get(i).getShowed() == true && this.moduleWSVO2s.get(i).getFunctionWSVO2s().length==0) {
							this.moduleWSVO2s.get(i).setShowed(false);
						}

					}
				
				}
			}
        }else {
        	logger.info("login java_jsf_tempate principal ="+principal);
        }
	}

	public void navigationListener(ActionEvent event) throws Exception {

		String obj = (String) event.getComponent().getAttributes().get("functionCode");

		// Remove managed bean of session
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("scopedTarget."+obj+"ManagedBean");

	}

	public boolean isGrant(String functionCode, String itemCode) {

		if (this.getDefaultSetting().getDevMode() == true) {
			return true;
		}
		if (this.getUserWSVO() != null && this.getUserWSVO().getGroupWSVOs() != null) {
			for (GroupWSVO groupWSVO : this.getUserWSVO().getGroupWSVOs()) {

				for (GroupFunctionWSVO groupFunctionWSVO : groupWSVO.getGroupFunctionWSVOs()) {
					if (groupFunctionWSVO.getFunctionItemWSVO().getCode().equals(itemCode)
							&& groupFunctionWSVO.getFunctionWSVO().getCode().equals(functionCode)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void onTabChange(TabChangeEvent event) {
		this.activeTab = Integer.parseInt(((AccordionPanel) event.getComponent()).getActiveIndex());

	}

	public Integer getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(Integer activeTab) {
		this.activeTab = activeTab;
	}

	public UserWSVO getUserWSVO() {
		return userWSVO;
	}

	public void setUserWSVO(UserWSVO userWSVO) {
		this.userWSVO = userWSVO;
	}

	public List<ModuleWSVO2> getModuleWSVO2s() {

		
		return moduleWSVO2s;
	}

	public void setModuleWSVO2s(List<ModuleWSVO2> moduleWSVO2s) {
		this.moduleWSVO2s = moduleWSVO2s;
	}


	public PortalClient getPortalClient() {
		return portalClient;
	}

	public void setPortalClient(PortalClient portalClient) {
		this.portalClient = portalClient;
	}

	public DefaultSetting getDefaultSetting() {
		return defaultSetting;
	}

	public void setDefaultSetting(DefaultSetting defaultSetting) {
		this.defaultSetting = defaultSetting;
	}



}
