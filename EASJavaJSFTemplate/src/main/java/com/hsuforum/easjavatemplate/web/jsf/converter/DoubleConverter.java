package com.hsuforum.easjavatemplate.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * Double coverter
 *
 */
public class DoubleConverter extends javax.faces.convert.NumberConverter {

	
	/**
	 * @see javax.faces.convert.NumberConverter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(final FacesContext facesContext,
			final UIComponent uiComponent, final String value) {
		
		final Number number = (Number) super.getAsObject(facesContext, uiComponent, value);
		Double result = null;
		if (number != null) {
			result = Double.valueOf(number.doubleValue());
		}
		return result;
	}
}
