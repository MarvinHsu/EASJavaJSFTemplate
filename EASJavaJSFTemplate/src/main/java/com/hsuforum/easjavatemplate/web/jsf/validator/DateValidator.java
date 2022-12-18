package com.hsuforum.easjavatemplate.web.jsf.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

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
	 * @see jakarta.faces.validator.Validator#validate(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
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
