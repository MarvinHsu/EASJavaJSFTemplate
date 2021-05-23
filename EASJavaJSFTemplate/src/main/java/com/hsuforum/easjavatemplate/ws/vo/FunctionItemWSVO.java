package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FunctionItemWSVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String url;
	
}
