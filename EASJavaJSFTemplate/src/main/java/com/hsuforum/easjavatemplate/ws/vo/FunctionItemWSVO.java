package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;

public class FunctionItemWSVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String url;
	
	public FunctionItemWSVO() {
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
