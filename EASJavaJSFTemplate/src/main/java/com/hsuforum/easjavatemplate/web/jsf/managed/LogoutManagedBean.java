package com.hsuforum.easjavatemplate.web.jsf.managed;

import java.io.IOException;
import java.io.Serializable;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletException;
/**
 * Logout managed bean
 * @author Marvin
 *
 */
public class LogoutManagedBean implements Serializable {

	private static final long serialVersionUID = -918579889145117388L;

	public String doLogoutAction() throws ServletException, IOException {

		final FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		externalContext.dispatch("/j_spring_security_logout.jspx");

		FacesContext.getCurrentInstance().responseComplete();

		return "index";
	}

}
