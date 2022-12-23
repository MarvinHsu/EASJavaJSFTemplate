package com.hsuforum.easjavatemplate.web.util;

import java.io.Serializable;

import jakarta.faces.model.SelectItem;

/**
 * Select item helper
 * @author Marvin
 *
 */
public class SelectHelper implements Serializable {

    private static final long serialVersionUID = -8609880167162019398L;

    
    
    public final static SelectItem EMPTY_SELECTITEM = new SelectItem("", "-----");

    public SelectHelper() {

    }


}
