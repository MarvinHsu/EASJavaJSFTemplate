package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FunctionWSVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Date createDate;
	private String name;
	private String outcome;
	private ModuleWSVO moduleWSVO;
	private Date updateDate;
	private Integer sequence;
	private Boolean showed;
	
	
}
