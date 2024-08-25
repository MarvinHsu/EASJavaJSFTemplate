package com.hsuforum.easjavatemplate.web.jsf.validator;

import org.apache.commons.lang3.math.NumberUtils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 * Number validator
 * @author Marvin
 *
 */
@FacesValidator("numberValidator")
public class NumberValidator implements Validator {

	
    /**
     * @see jakarta.faces.validator.Validator#validate(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
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
