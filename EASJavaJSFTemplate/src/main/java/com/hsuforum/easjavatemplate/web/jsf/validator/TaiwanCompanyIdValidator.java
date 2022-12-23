package com.hsuforum.easjavatemplate.web.jsf.validator;

import java.util.regex.Pattern;

import com.hsuforum.common.web.util.MessageUtils;
import com.hsuforum.easjavatemplate.common.ErrorCode;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
/**
 * Taiwan company id validator
 * @author Marvin
 *
 */
@FacesValidator("taiwanCompanyIdValidator")
public class TaiwanCompanyIdValidator implements Validator {
    private final Pattern PATTERN = Pattern.compile("^[0-9]{8}$");
    private final int[] BASE_MULTIPLIER = new int[] { 1, 2, 1, 2, 1, 2, 4, 1 };
    

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean validate = false;
        String companyId = (String) value;
        int num = 0;
        int sum = 0;
        if (PATTERN.matcher(companyId).matches()) {
            boolean isSeven = companyId.charAt(6) == '7' ? true : false;
            for (int i = 0; i < 8; i++) {
                num = Integer.parseInt(companyId.substring(i, i + 1)) * BASE_MULTIPLIER[i];
                sum += (num / 10) + (num % 10);
            }
            if (sum % 10 == 0) {
                validate = true;
            } else if (isSeven && (sum + 1) % 10 == 0) {
                validate = true;
            }

        }
        if (!validate) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getAccessor().getMessage(ErrorCode.P10010.toString()),
                    null);
            throw new ValidatorException(message);
        }
    }
    
   
}
