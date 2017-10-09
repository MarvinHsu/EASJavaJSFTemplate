package com.hsuforum.easjavatemplate.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.context.support.MessageSourceAccessor;

import com.hsuforum.common.web.util.MessageUtils;
import com.hsuforum.common.web.util.StringUtils;

/**
 * Boolean converter
 * @author Marvin
 *
 */
@FacesConverter("booleanConvert")
public class BooleanConvert implements Converter {

	private MessageSourceAccessor accessor = MessageUtils.getAccessor();

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Boolean enabled = Boolean.FALSE;

		try {
			if (StringUtils.isNotBlank(value)) {
				enabled = Boolean.getBoolean(value);
			}
		} catch (Exception e) {
			throw new ConverterException(e.getLocalizedMessage());
		}
		return enabled;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String display = null;
		String type = (String) component.getAttributes().get("booleanType");

		try {
			boolean enabled = (Boolean) value;
			if (enabled) {
				if ("1".equals(type)) {
					// true
					display = accessor.getMessage("true");
				} else if ("2".equals(type)) {
					// Y
					display = "Y";
				} else {
					display = "true";
				}
			} else {
				if ("1".equals(type)) {
					// false
					display = accessor.getMessage("false");
				} else if ("2".equals(type)) {
					// N
					display = "N";
				} else {
					display = "false";
				}
			}
		} catch (Exception e) {
			throw new ConverterException(e);
		}
		return StringUtils.stripToEmpty(display);
	}

}
