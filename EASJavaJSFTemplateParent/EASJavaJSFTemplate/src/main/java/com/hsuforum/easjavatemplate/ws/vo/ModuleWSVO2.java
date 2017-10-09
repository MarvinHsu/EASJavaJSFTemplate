package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;

public class ModuleWSVO2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private int sequence;
	
	private FunctionWSVO2[] functionWSVO2s;
	// 是否在選單顯示
	private Boolean showed = false;
	public ModuleWSVO2(){
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

	public FunctionWSVO2[] getFunctionWSVO2s() {
		return functionWSVO2s;
	}

	public void setFunctionWSVO2s(FunctionWSVO2[] functionWSVO2s) {
		this.functionWSVO2s = functionWSVO2s;
	}

	


	
	
	
}
