package com.hsuforum.easjavatemplate.web.jsf.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.utils.JSFUtils;
import com.hsuforum.easjavatemplate.web.config.DefaultConfigManagedBean;
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
	private DefaultConfigManagedBean defaultConfigManagedBean;

	@Autowired
	private PortalClient portalClient;

	@Autowired
	private String systemId;
	private UserWSVO userWSVO;
	private List<ModuleWSVO2> moduleWSVO2s;

	private Integer activeTab;

	public MenuManagedBean() {
		super();

	}

	@PostConstruct
	public void init() {
		// get user id
        AttributePrincipal principal = (AttributePrincipal)JSFUtils.getHttpServletRequest().getUserPrincipal();        
        Map attributes = principal.getAttributes();
        String userId=(String)attributes.get("sAMAccountName");
		//this.userId = JSFUtils.getHttpServletRequest().getRemoteUser();
        logger.info("login java_jsf_tempate user id ="+userId);
        
		this.userWSVO = this.getPortalClient().findUserById(this.getSystemId(), userId.toUpperCase());
		ModuleWSVO2[] moduleArray = this.getPortalClient().findModuleBySystem(this.getSystemId());

		this.moduleWSVO2s = new ArrayList<ModuleWSVO2>();
		if (moduleArray != null) {
			for (int j = 0; j < moduleArray.length; j++) {
				this.moduleWSVO2s.add(moduleArray[j]);
			}

			if (this.moduleWSVO2s.size() > 0) {
				this.activeTab = 0;
			}
			if (this.userWSVO != null && userWSVO.getGroupWSVOs() != null) {
				for (GroupWSVO groupWSVO : userWSVO.getGroupWSVOs()) {

					for (GroupFunctionWSVO groupFunctionWSVO : groupWSVO.getGroupFunctionWSVOs()) {
						for (int i = 0; i < this.moduleWSVO2s.size(); i++) {
							if (groupFunctionWSVO.getFunctionWSVO().getModuleWSVO() != null
									&& groupFunctionWSVO.getFunctionWSVO().getModuleWSVO().getCode()
											.equals(this.moduleWSVO2s.get(i).getCode())) {
								this.moduleWSVO2s.get(i).setShowed(true);
								FunctionWSVO2[] iter = this.moduleWSVO2s.get(i).getFunctionWSVO2s();
								FunctionWSVO2[] functionWSVO2s = new FunctionWSVO2[iter.length];
								for (int j = 0; j < iter.length; j++) {
									FunctionWSVO2 functionWSVO2 = iter[j];
									if (groupFunctionWSVO.getFunctionWSVO().getCode().equals(functionWSVO2.getCode())) {
										functionWSVO2.setShowed(true);
									}
									functionWSVO2s[j] = functionWSVO2;
								}
								this.moduleWSVO2s.get(i).setFunctionWSVO2s(functionWSVO2s);

							}

						}
					}
				}
			}
		}
	}

	public void navigationListener(ActionEvent event) throws Exception {

		String obj = (String) event.getComponent().getAttributes().get("functionCode");

		// Remove managed bean of session
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove(obj+"ManagedBean");

	}

	public boolean isGrant(String functionCode, String itemCode) {

		if (this.getDefaultConfigManagedBean().getDevMode() == true) {
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

	public DefaultConfigManagedBean getDefaultConfigManagedBean() {
		return defaultConfigManagedBean;
	}

	public void setDefaultConfigManagedBean(DefaultConfigManagedBean defaultConfigManagedBean) {
		this.defaultConfigManagedBean = defaultConfigManagedBean;
	}

	public PortalClient getPortalClient() {
		return portalClient;
	}

	public void setPortalClient(PortalClient portalClient) {
		this.portalClient = portalClient;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

}
