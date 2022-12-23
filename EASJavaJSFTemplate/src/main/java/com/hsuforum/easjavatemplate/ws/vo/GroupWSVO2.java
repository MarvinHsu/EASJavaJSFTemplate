package com.hsuforum.easjavatemplate.ws.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupWSVO2 implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;

	
	@Override
	public String getAuthority() {
		return "ROLE_" + this.getName();
	}
}
