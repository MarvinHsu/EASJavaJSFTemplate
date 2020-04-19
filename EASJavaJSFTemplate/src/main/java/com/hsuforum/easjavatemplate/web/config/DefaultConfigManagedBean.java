package com.hsuforum.easjavatemplate.web.config;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.hsuforum.common.web.jsf.utils.JSFUtils;

/**
 * Default configuration managed bean
 *
 */
@Component
@ApplicationScope
public class DefaultConfigManagedBean implements Serializable {

	private static final long serialVersionUID = -5125088362383666757L;
	
	private String systemCode="EAS";
	private Boolean devMode=true;
	private Integer rowsOfPerPage=25;
	private String mailFrom="abc@sample.com";
	private String timeZone="Asia/Taipei";
	public Integer getRowsOfPerPage() {
		return rowsOfPerPage;
	}

	public void setRowsOfPerPage(Integer rowsOfPerPage) {
		this.rowsOfPerPage = rowsOfPerPage;
	}

	public Boolean getDevMode() {
		return devMode;
	}

	public void setDevMode(Boolean devMode) {
		this.devMode = devMode;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String doSelectSkin() {
		HttpServletResponse response = (HttpServletResponse) JSFUtils.getFacesContext().getExternalContext()
				.getResponse();
		Cookie cookie = new Cookie("skinFamily", null);
		cookie.setMaxAge(2592000);
		response.addCookie(cookie);

		return JSFUtils.getFacesContext().getViewRoot().getViewId();

	}

	/**
	 * Get managed bean of session
	 *
	 * @return
	 */
	public static DefaultConfigManagedBean getManagedBean() {
		return (DefaultConfigManagedBean) JSFUtils.getManagedBean("defaultConfigManagedBean");
	}
}
