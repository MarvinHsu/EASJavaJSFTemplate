package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;
import java.util.Date;

public class UserWSVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private boolean enabled;
	private String name;
	private String password;
	private String email;
	private Date createDate;
	private Date updateDate;
	private GroupWSVO[] groupWSVOs;
	public UserWSVO(){
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public GroupWSVO[] getGroupWSVOs() {
		return groupWSVOs;
	}
	public void setGroupWSVOs(GroupWSVO[] groupWSVOs) {
		this.groupWSVOs = groupWSVOs;
	}
	
	
}
