package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class GroupFunctionWSVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private FunctionWSVO functionWSVO;
	private FunctionItemWSVO functionItemWSVO;
	
	
}
