package com.hsuforum.easjavatemplate.web.jsf.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

/**
 *
 * Double coverter
 *
 */
public class DoubleConverter extends jakarta.faces.convert.NumberConverter {

	
	/**
	 * @see jakarta.faces.convert.NumberConverter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
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
