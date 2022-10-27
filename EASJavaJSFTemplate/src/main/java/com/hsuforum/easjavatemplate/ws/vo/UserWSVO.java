package com.hsuforum.easjavatemplate.ws.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserWSVO implements Serializable,UserDetails{
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

	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> arrayList = new ArrayList<GrantedAuthority>();
		for (GroupWSVO groupWSVO : this.getGroupWSVOs()) {
			GrantedAuthority ga = (GrantedAuthority) groupWSVO;
			arrayList.add(ga);
		}

		return arrayList;
	}
	@Override
	public String getUsername() {
		return this.account;
	}

		
}
