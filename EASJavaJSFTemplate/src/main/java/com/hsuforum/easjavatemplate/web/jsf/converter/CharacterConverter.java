package com.hsuforum.easjavatemplate.web.jsf.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;


/**
 * Half-angle to holomorphic converter
 * @author Marvin
 *
 */
public class CharacterConverter implements Converter {
	private final static int OFFSET = (int) 'Ａ' - (int) 'A';

	/**
	 * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
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
	 * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
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
