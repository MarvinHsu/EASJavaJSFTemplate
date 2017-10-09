package com.hsuforum.easjavatemplate.entity;

/**
 * Function code enum
 * @author Marvin
 *
 */
public enum FunctionCode {
	
	/** MasterManagedBean UC1.1 Master config */
	UC1_1("master", "MasterManagedBean"),
	/** DetailManagedBean UC1.2 Detail config */
	UC1_2("detail", "DetailManagedBean"),
	/** Many1ManagedBean UC1.3 Many1 config */
	UC1_3("many1", "Many1ManagedBean");


	private String code;

	private String managedBean;

	FunctionCode(String code, String managedBean) {
		this.code = code;
		this.managedBean = managedBean;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ManagedBena id in jsf
	 *
	 * @return managedBean id
	 */
	public String getManagedBean() {
		return this.managedBean;
	}

}
