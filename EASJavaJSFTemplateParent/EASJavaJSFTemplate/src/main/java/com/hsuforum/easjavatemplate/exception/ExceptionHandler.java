package com.hsuforum.easjavatemplate.exception;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.OptimisticLockException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.transaction.TransactionSystemException;

import com.hsuforum.easjavatemplate.common.ErrorCode;

/**
 * 例外處理類別
 * @author Marvin
 *
 */
@Aspect
public class ExceptionHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Pointcut("execution(* com.hsuform.eas.service.*Service.*(..))")
	public void businessService() {
	}

	@Pointcut("execution(* com.hsuforum.easjavatemplate.web.jsf.managed..*ManagedBean.do*Action(..))")
	public void doActionInWebLayer() {
	}

	@AfterThrowing(pointcut = "businessService()", throwing = "ex")
	public void translateException(Throwable ex) {

	}

	@Around("doActionInWebLayer()")
	public Object handleUnprocessedException(ProceedingJoinPoint pjp) throws Throwable {
		try {
			return pjp.proceed();
		} catch (CompositeProjectRuntimeException e) {
			List<ProjectRuntimeException> exceptions = ((CompositeProjectRuntimeException) e).getExceptions();
			for (ProjectRuntimeException exception : exceptions) {
				this.handleException(exception);
			}
			return null;
		} catch (ProjectRuntimeException e) {
			this.handleException(e);
			return null;
		} catch (TransactionSystemException e) {
			this.handleException(e);
			return null;
		} catch (OptimisticLockException e) {
			ProjectRuntimeException throwable = new ProjectRuntimeException(ErrorCode.P10007, e);
			this.handleException(throwable);
			return null;
		} catch (OptimisticLockingFailureException e) {
			ProjectRuntimeException throwable = new ProjectRuntimeException(ErrorCode.P10007, e);
			this.handleException(throwable);
			return null;
		} catch (DataAccessException e) {
			this.handleException(e);
			return null;
		} catch (Exception e) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Error class = " + e.getClass());
			}
			throw e;
		}
	}

	public void handleException(Throwable t) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (t instanceof ProjectRuntimeException) {
			logger.warn(t.getMessage(), t);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), t.getMessage());
			context.addMessage(((ProjectRuntimeException) t).getSourceId(), message);
		} else if (t instanceof TransactionSystemException) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), t.getMessage());
			context.addMessage(null, message);
		} else if (t instanceof OptimisticLockException) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), t.getMessage());
			context.addMessage(null, message);
		} else if (t instanceof OptimisticLockingFailureException) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), t.getMessage());
			context.addMessage(null, message);
		} else if (t instanceof DataAccessException) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), t.getMessage());
			context.addMessage(null, message);
		}
	}

}
