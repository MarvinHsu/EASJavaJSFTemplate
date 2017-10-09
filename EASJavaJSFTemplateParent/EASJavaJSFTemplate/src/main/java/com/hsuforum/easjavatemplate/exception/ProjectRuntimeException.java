package com.hsuforum.easjavatemplate.exception;

import com.hsuforum.common.web.util.MessageUtils;
import com.hsuforum.easjavatemplate.common.ErrorCode;


/**
 * Base runtime exception class
 * @author Marvin
 *
 */
public class ProjectRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1137940234928859514L;

	private ErrorCode errorCode;

	private String[] params;

	private String sourceId;

	public ProjectRuntimeException(ErrorCode errorCode) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name()));
		this.errorCode = errorCode;
	}

	public ProjectRuntimeException(ErrorCode errorCode, String[] params) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name(), params));
		this.errorCode = errorCode;
		this.params = params;
	}

	public ProjectRuntimeException(ErrorCode errorCode, Throwable cause) {
		super(MessageUtils.getAccessor().getMessage(errorCode.name()), cause);
		this.errorCode = errorCode;
	}

	public ProjectRuntimeException(ErrorCode errorCode, String[] params, Throwable cause) {
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

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getCauseMessage() {
		StringBuffer message = new StringBuffer();
		Throwable cause = getCause();
		if (cause != null) {
			if (cause instanceof ProjectRuntimeException) {
				ProjectRuntimeException t = (ProjectRuntimeException) getCause();
				if (t != null) {
					message.append(t.getCauseMessage());
				}
			}
		}
		message.append(MessageUtils.getAccessor().getMessage(errorCode.name(), this.getParams()) + "\n,");
		return message.toString();
	}

}
