package com.hsuforum.easjavatemplate.web.config;

import java.io.Serializable;

public class JobConfigBean implements Serializable {
	private static final long serialVersionUID = 5913706552232111030L;
	private Integer dataTableRows;

	public JobConfigBean() {
		super();
	}

	public void setDataTableRows(Integer dataTableRows) {
		this.dataTableRows = dataTableRows;
	}

	public Integer getDataTableRows() {
		return dataTableRows;
	}
}
