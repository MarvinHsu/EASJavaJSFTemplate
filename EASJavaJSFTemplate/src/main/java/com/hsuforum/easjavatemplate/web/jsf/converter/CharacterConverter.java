package com.hsuforum.easjavatemplate.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


/**
 * Half-angle to holomorphic converter
 * @author Marvin
 *
 */
public class CharacterConverter implements Converter {
	private final static int OFFSET = (int) 'Ａ' - (int) 'A';

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		String result = null;
		if (value != null) {
			value = value.trim();
			// 先將空白替換
			value = value.replace(' ', '　');
			StringBuffer sb = new StringBuffer();
			for (int i = 0, n = value.length(); i < n; ++i) {
				char ch = value.charAt(i);
				// ! 是 Ascii 內, 要被處理的第一個符號
				// ~ 則是最後一個
				if ((ch >= (int) '!') && ch <= (int) '~') {
					sb.append((char) (ch + OFFSET));
				} else {
					sb.append(ch);
				}
			}
			result = sb.toString();
		}
		return result;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		String result = null;
		if (value != null) {
			result = value.toString();
		}
		return result;
	}

}
