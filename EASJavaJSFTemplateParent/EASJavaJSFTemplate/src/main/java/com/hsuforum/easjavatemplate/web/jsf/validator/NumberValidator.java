package com.hsuforum.easjavatemplate.web.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Number validator
 * @author Marvin
 *
 */
@FacesValidator("numberValidator")
public class NumberValidator implements Validator {

	
    /**
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent,
            Object value) throws ValidatorException {
        if (value == null) return;
        try {
            if (!NumberUtils.isNumber(value.toString())) {
                throw new NumberFormatException();
            }
		}
		catch(NumberFormatException e) {
			throw new ValidatorException(
					new FacesMessage(e.getMessage(), null));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

    }

}
