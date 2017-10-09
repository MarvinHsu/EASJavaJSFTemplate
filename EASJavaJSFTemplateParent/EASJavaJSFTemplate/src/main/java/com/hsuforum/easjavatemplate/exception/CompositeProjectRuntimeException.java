package com.hsuforum.easjavatemplate.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Marvin
 *
 */
public class CompositeProjectRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 986551174680082293L;
	private List<ProjectRuntimeException> exceptions = new ArrayList<ProjectRuntimeException>();

	public List<ProjectRuntimeException> getExceptions() {
		return this.exceptions;
	}

	public void add(ProjectRuntimeException e) {
		this.exceptions.add(e);
	}

	public boolean hasException() {
		return exceptions.size() > 0;
	}

}
