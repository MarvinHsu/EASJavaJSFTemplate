package com.hsuforum.easjavatemplate.web.jsf.converter;

import java.math.BigDecimal;

import com.hsuforum.common.web.util.StringUtils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.html.HtmlInputText;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;


/**
 * Amount converter
 * @author Marvin
 *
 */
@FacesConverter("amountConvert")
public class AmountConvert implements Converter {

	/**
	 * @see jakarta.faces.convert.Converter#getAsObject(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		boolean isDefaultZero = true;
		try {
			String defaultZero = (String) component.getAttributes().get("defaultZero");
			if (defaultZero != null) {
				isDefaultZero = new Boolean(defaultZero);
			}
		} catch (Exception e) {

			throw e;
		}

		BigDecimal amt = null;
		if (isDefaultZero) {
			amt = BigDecimal.ZERO;
		}

		if (StringUtils.isNotBlank(value)) {
			amt = new BigDecimal(value);
		} else {
			return null;
		}
		

		return amt.setScale(0);
	}

	/**
	 * @see jakarta.faces.convert.Converter#getAsString(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		BigDecimal amount = null;

		try {
			amount = (BigDecimal) value;
		} catch (Exception e) {
			throw new ConverterException(e.getLocalizedMessage());
		}

		if (amount == null) {
			return "";
		} else {
			if (component instanceof HtmlInputText) {
				HtmlInputText inputText = (HtmlInputText) component;
				if (inputText.isReadonly()) {
					return StringUtils.getMoneyStr(amount.toString(), 0);
				} else {
					return amount.toString();
				}
			} else {
				return StringUtils.getMoneyStr(amount.toString(), 0);
			}
		}

	}

}
