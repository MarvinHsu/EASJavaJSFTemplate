package com.hsuforum.easjavatemplate.exception;

import com.hsuforum.common.web.util.MessageUtils;
import com.hsuforum.easjavatemplate.common.ErrorCode;


/**
 * Base checked exception class
 * @author Marvin
 *
 */
public class ProjectException extends Exception {

	private static final long serialVersionUID = 7482238675438842226L;

	private ErrorCode errorCode;

	private String[] params;

	public ProjectException(ErrorCode errorCode) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name()));
		this.errorCode = errorCode;
	}

	public ProjectException(ErrorCode errorCode, String[] params) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name(), params));
		this.errorCode = errorCode;
		this.params = params;
	}

	public ProjectException(ErrorCode errorCode, Throwable cause) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name()), cause);
		this.errorCode = errorCode;
	}

	public ProjectException(ErrorCode errorCode, String[] params, Throwable cause) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name(), params), cause);
		this.errorCode = errorCode;
		this.params = params;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String[] getParams() {
		return params;
	}

}
