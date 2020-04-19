package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;

public class GroupFunctionWSVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private FunctionWSVO functionWSVO;
	private FunctionItemWSVO functionItemWSVO;
	
	
	public GroupFunctionWSVO() {
		super();
	}
	
	public FunctionWSVO getFunctionWSVO() {
		return functionWSVO;
	}
	public void setFunctionWSVO(FunctionWSVO functionWSVO) {
		this.functionWSVO = functionWSVO;
	}
	public FunctionItemWSVO getFunctionItemWSVO() {
		return functionItemWSVO;
	}
	public void setFunctionItemWSVO(FunctionItemWSVO functionItemWSVO) {
		this.functionItemWSVO = functionItemWSVO;
	}


	
	
	
}
