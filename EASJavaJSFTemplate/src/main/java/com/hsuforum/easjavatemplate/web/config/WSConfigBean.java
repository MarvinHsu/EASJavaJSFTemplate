package com.hsuforum.easjavatemplate.web.config;

import java.io.Serializable;

import com.hsuforum.easjavatemplate.DefaultSetting;

public class WSConfigBean implements Serializable {

	private static final long serialVersionUID = 5913706553532111030L;
	// 一般在data Table所顯示的table筆數
	// WS的存取token
	private String accessToken;
	private String siteAccessToken;
	private Integer dataTableRows;
	private String mailFrom;

	public WSConfigBean() {
		super();
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setSiteAccessToken(String siteAccessToken) {
		this.siteAccessToken = siteAccessToken;
	}

	public String getSiteAccessToken() {
		return siteAccessToken;
	}

	public Integer getDataTableRows() {
		return dataTableRows;
	}

	public void setDataTableRows(Integer dataTableRows) {
		this.dataTableRows = dataTableRows;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailFrom() {
		return mailFrom;
	}
}
