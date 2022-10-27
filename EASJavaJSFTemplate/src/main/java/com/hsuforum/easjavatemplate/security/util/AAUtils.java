package com.hsuforum.easjavatemplate.security.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Authentication & Authorization utility
 * @author Marvin
 *
 */
public class AAUtils {

	/**
	 * Get login user
	 * @return
	 */
	public static Object getLoggedInUser() {
		SecurityContext sc = SecurityContextHolder.getContext();
		if (sc == null) {
			return null;
		} else {
			if (sc.getAuthentication() != null) {
				return sc.getAuthentication().getPrincipal();
			} else {
				return null;
			}
		}
	}

	/**
	 * Get login user ip
	 *
	 * @return
	 */
	public static String getRemoteAddress() {
		WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder.getContext()
				.getAuthentication().getDetails();
		return details.getRemoteAddress();
	}

}
