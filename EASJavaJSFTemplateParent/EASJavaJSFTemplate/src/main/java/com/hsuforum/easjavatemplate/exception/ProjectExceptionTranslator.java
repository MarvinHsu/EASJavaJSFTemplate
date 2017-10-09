package com.hsuforum.easjavatemplate.exception;

import javax.persistence.OptimisticLockException;

import org.springframework.dao.DataAccessException;

import com.hsuforum.easjavatemplate.common.ErrorCode;

/**
 * Transfer Exception to ProjectExcetion
 * @author Marvin
 *
 */
public class ProjectExceptionTranslator {

	private ProjectExceptionTranslator() {
	}

	/**
	 * 轉換Exception為ProjectException
	 * 
	 * @param Throwable
	 *            t
	 * @return ProjectException
	 */
	public static ProjectException translatingException(Throwable t) {

		ErrorCode errorCode = ErrorCode.P10006; // 預設為未知的錯誤
		String[] params = null;

		if (t instanceof ProjectException) {
			return (ProjectException) t;
		}
		// 同一筆資料同時被寫入
		if (t instanceof OptimisticLockException) {
			errorCode = ErrorCode.P10007;
		}
		// 資料存取錯誤
		else if (t instanceof DataAccessException) {
			errorCode = ErrorCode.P10008;
		}

		ProjectException ex = new ProjectException(errorCode, params, t);

		return ex;
	}

}
