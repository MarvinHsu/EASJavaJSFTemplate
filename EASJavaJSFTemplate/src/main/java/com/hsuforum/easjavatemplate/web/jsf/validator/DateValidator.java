package com.hsuforum.easjavatemplate.web.jsf.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Date validator
 * @author Marvin
 *
 */
@FacesValidator("dateValidator")
public class DateValidator implements Validator {

	public final static SimpleDateFormat DEFAULT_FORMAT =
		new SimpleDateFormat("yyyy/M/d");

	
	/**
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validate(FacesContext facesContext, UIComponent uiComponent
			, Object value) throws ValidatorException {
		
		if (value == null) return;
		try {
			 if (uiComponent instanceof UIInput) {
								
				UIInput uiInput = (UIInput) uiComponent;
				if (uiInput.getSubmittedValue().toString().trim().equals
						(DEFAULT_FORMAT.format((Date)value))) {
					return;
				}
			}
			throw new Exception();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ValidatorException(
					new FacesMessage(e.getMessage(), null));
		}
		
	}
}
