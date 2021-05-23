package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
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
		
}
