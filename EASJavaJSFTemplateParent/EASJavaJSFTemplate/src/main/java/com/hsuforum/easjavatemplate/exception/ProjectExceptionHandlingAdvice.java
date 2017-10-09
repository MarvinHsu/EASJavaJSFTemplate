package com.hsuforum.easjavatemplate.exception;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/**
 * Advice to translate all exception to ProjectException
 * @author Marvin
 *
 */
public class ProjectExceptionHandlingAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Throwable t) throws Exception {

		// 將Service Layer傳來的Exception全部轉為ServiceException
		ProjectException ex = ProjectExceptionTranslator.translatingException(t);
		throw ex;

	}

}
