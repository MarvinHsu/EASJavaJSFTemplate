/**
 *
 */
package com.hsuforum.easjavatemplate.web.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.hsuforum.common.web.jsf.utils.JSFUtils;

/**
 * 
 * @author Marvin
 *
 */
@ManagedBean
@SessionScoped
public class SkinBean implements Serializable {

	private static final long serialVersionUID = -974102925798731707L;
	@ManagedProperty(value = "red")
	private String skin;
	private List<String> skins;

	@PostConstruct
	public void initialize() {
		skins = new ArrayList<String>();

		skins.add("red");
		skins.add("blue");
		skins.add("glass-x");
		skins.add("casablanca");
		skins.add("afterwork");

		Map<String, Object> cookies = JSFUtils.getFacesContext().getExternalContext().getRequestCookieMap();
		Cookie getCookie = (Cookie) cookies.get("skin");
		if (getCookie != null) {
			skin = getCookie.getValue();
		}
	}

	public String getSkin() {

		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public List<String> getSkins() {
		return skins;
	}

	public String doSelectSkin() {
		HttpServletResponse response = (HttpServletResponse) JSFUtils.getFacesContext().getExternalContext()
				.getResponse();
		Cookie cookie = new Cookie("skin", skin);
		cookie.setMaxAge(2592000);
		response.addCookie(cookie);

		return JSFUtils.getFacesContext().getViewRoot().getViewId();

	}
}
