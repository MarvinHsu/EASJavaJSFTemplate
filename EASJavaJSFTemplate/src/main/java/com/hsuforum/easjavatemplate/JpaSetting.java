package com.hsuforum.easjavatemplate;

import java.io.Serializable;

import lombok.Data;
/**
 * Jpa properties setting class
 * @author Marvin
 */
@Data
public class JpaSetting  implements Serializable{ 
	private static final long serialVersionUID = 2349539934240258333L;
	private String physicalStrategy;
	private String ddlAuto;
	private Boolean showSql;
	private String entityCopyObserver;
	private String formatSql;
	private String dialect;
}
