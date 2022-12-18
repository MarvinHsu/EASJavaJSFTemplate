package com.hsuforum.easjavatemplate.web.jsf.converter;

import java.util.Calendar;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import com.hsuforum.common.web.util.DateUtils;
import com.hsuforum.common.web.util.StringUtils;

/**
 * Taiwan date converter。
 * @author Marvin
 *
 */
@FacesConverter("taiwanDateConverter")
public class TaiwanDateConvert implements Converter {

	/**
	 * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Calendar c = null;
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(value)) {
				c = DateUtils.getCalendarByTaiwanStr(value);
				flag = true;
			}
			if (c == null && flag) {
				throw new ConverterException();
			}
		} catch (Exception e) {
			
			throw e;
		}
		return c;
	}

	/**
	 * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Calendar c = null;
		
		int len = 7;
		try {
			String length = (String) component.getAttributes().get("length");
			if (length != null) {
				len = Integer.parseInt(length);
			}
		} catch (Exception e) {
			
			throw  e;
		}

		try {
			if (value == null) {
				return "";
			}
			c = (Calendar) value;
		} catch (Exception e) {
			
			throw e;
		}
		
		return StringUtils.substring(StringUtils.leftPad(DateUtils.getTaiwanDateStr(c.getTime(), "", false), 7, "0"), 0,
				len);
	}

}
