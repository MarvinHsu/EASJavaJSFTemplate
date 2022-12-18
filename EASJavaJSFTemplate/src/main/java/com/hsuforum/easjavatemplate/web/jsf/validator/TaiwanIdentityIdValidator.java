package com.hsuforum.easjavatemplate.web.jsf.validator;

import java.util.Arrays;

import com.hsuforum.common.web.util.MessageUtils;
import com.hsuforum.easjavatemplate.common.ErrorCode;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;


/**
 * Taiwan identity id validator
 * @author Marvin
 *
 */
@FacesValidator("taiwanIdentityIdValidator")
public class TaiwanIdentityIdValidator implements Validator {
	
    /**
     * @see jakarta.faces.validator.Validator#validate(jakarta.faces.context.FacesContext, jakarta.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean validate = false;
        if (value != null) {
            String identityId = ((String) value).trim();
            if (identityId.length() == 10) {
                if (checkIdentityId(identityId)) {
                    validate = true;
                }
            }
        }
        if (!validate) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtils.getAccessor().getMessage(ErrorCode.P10009.toString()),
                    null);
            throw new ValidatorException(message);
        }
    }

    
    /**
     * 
     * 
     * @return
     */
    private boolean checkIdentityId(String str) {
    	str=str.toUpperCase();
    	if (str == null || "".equals(str)) {
    		return false;
    	}
    		
    	final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    	
    	final int[] pidIDInt = { 1, 10, 19, 28, 37, 46, 55, 64, 39, 73, 82, 2, 11, 20, 48, 29, 38, 47, 56, 65, 74, 83, 21, 3, 12, 30 };

    	
    	final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2, 2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3, 12, 10 };
    	
    	
    	final int[] pidResidentSecondInt = {0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2, 0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6, 0, 8, 4};
    		
    	str = str.toUpperCase();
    	final char[] strArr = str.toCharArray();
    	int verifyNum = 0;

    	
    	if (str.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")) {
    		
    		verifyNum = verifyNum + pidIDInt[Arrays.binarySearch(pidCharArray, strArr[0])];
    		
    		for (int i = 1, j = 8; i < 9; i++, j--) {
    			verifyNum += Character.digit(strArr[i], 10) * j;
    		}
    		
    		verifyNum = (10 - (verifyNum % 10)) % 10;
    		
    		return verifyNum == Character.digit(strArr[9], 10);
    	}

    		
    		verifyNum = 0;
    	if (str.matches("[A-Z]{1}[A-D]{1}[0-9]{8}")) {
    		
    		verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray, strArr[0])];
    		
    		verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArray, strArr[1])];
    		
    		for (int i = 2, j = 7; i < 9; i++, j--) {
    			verifyNum += Character.digit(strArr[i], 10) * j;
    		}
    		
    		verifyNum = (10 - (verifyNum % 10)) % 10;
    		
    		return verifyNum == Character.digit(strArr[9], 10);
    	}
    	
    	return false;
    }
}
