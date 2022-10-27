package com.hsuforum.easjavatemplate.security.vote;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.hsuforum.easjavatemplate.ws.vo.FunctionItemWSVO;
import com.hsuforum.easjavatemplate.ws.vo.GroupFunctionWSVO;
import com.hsuforum.easjavatemplate.ws.vo.GroupWSVO;
import com.hsuforum.easjavatemplate.ws.vo.UserWSVO;

/**
 * User voter
 * @author Marvin
 *
 */
public class UserVoter extends RoleVoter {

	
	private final static String URI_ENDFIX = "jsf";

	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> config) {
		int result = ACCESS_ABSTAIN;

		Object principal = authentication.getPrincipal();
		if (!(principal instanceof UserWSVO)) {
			return result;
		}
		UserWSVO userWSVO = (UserWSVO) principal;
		GroupWSVO[] groupWSVOs = userWSVO.getGroupWSVOs();

		if (!(object instanceof FilterInvocation)) {
			return result;
		}
		FilterInvocation fi = (FilterInvocation) object;

		Iterator iter = config.iterator();
		while (iter.hasNext()) {
			ConfigAttribute attribute = (ConfigAttribute) iter.next();

			if (super.supports(attribute)) {
				result = ACCESS_DENIED;
				for (GroupWSVO groupWSVO : groupWSVOs) {
					for (GroupFunctionWSVO groupFunctionWSVO : groupWSVO.getGroupFunctionWSVOs()) {
						FunctionItemWSVO functionItemWSVO = groupFunctionWSVO.getFunctionItemWSVO();
						if (functionItemWSVO != null) {

							if (fi.getRequestUrl().substring(0, fi.getRequestUrl().indexOf(URI_ENDFIX) + 3)
									.equals(functionItemWSVO.getUrl())) {
								return ACCESS_GRANTED;
							}
						}

					}
				}
			}
		}
		return result;
	}

}
