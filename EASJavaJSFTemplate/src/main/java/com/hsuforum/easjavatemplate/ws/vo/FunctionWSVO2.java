package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;
import java.util.Date;

public class FunctionWSVO2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Date createDate;
	private String name;
	private String outcome;
	
	private Date updateDate;
	private int sequence;
	private Boolean showed;
	

	public FunctionWSVO2() {
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	


	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public Boolean getShowed() {
		return showed;
	}
	public void setShowed(Boolean showed) {
		this.showed = showed;
	}

	
	
	
	
}
