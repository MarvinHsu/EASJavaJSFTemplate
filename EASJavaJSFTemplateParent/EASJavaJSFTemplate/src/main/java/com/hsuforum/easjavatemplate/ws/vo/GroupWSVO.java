package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;

public class GroupWSVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private GroupFunctionWSVO[] groupFunctionWSVOs;
	
	public GroupWSVO() {
		super();
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GroupFunctionWSVO[] getGroupFunctionWSVOs() {
		return groupFunctionWSVOs;
	}
	public void setGroupFunctionWSVOs(GroupFunctionWSVO[] groupFunctionWSVOs) {
		this.groupFunctionWSVOs = groupFunctionWSVOs;
	}
}
