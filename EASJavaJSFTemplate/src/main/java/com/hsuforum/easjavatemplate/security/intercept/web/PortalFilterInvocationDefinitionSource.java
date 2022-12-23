package com.hsuforum.easjavatemplate.security.intercept.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.hsuforum.easjavatemplate.DefaultSetting;
import com.hsuforum.easjavatemplate.ws.client.PortalClient;
import com.hsuforum.easjavatemplate.ws.vo.FunctionItemWSVO;
import com.hsuforum.easjavatemplate.ws.vo.GroupFunctionWSVO;
import com.hsuforum.easjavatemplate.ws.vo.GroupWSVO2;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Filter invocation definition source
 * @author Marvin
 *
 */
public class PortalFilterInvocationDefinitionSource implements FilterInvocationSecurityMetadataSource {

	protected final Log logger = LogFactory.getLog(getClass());

	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	private PortalClient portalClient;


	private DefaultSetting defaultSetting;
	public void init() {
		requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		GroupFunctionWSVO[] groupFunctionWSVOs = portalClient.findGroupFunctionBySystem(this.getDefaultSetting().getSystemId());
		
		for (GroupFunctionWSVO groupFunctionWSVO : groupFunctionWSVOs) {
			List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			GroupWSVO2 groupWSVO2 = groupFunctionWSVO.getGroupWSVO2();

			configAttributes.add(new SecurityConfig(groupWSVO2.getAuthority()));

			Collection<ConfigAttribute> definition = new ArrayList<ConfigAttribute>(configAttributes);

			FunctionItemWSVO funtionItemWSVO = groupFunctionWSVO.getFunctionItemWSVO();

			RequestMatcher requestMatcher = new AntPathRequestMatcher(funtionItemWSVO.getUrl());
			requestMap.put(requestMatcher, definition);

			if (logger.isDebugEnabled()) {
				Iterator iter = definition.iterator();

				StringBuffer sb = new StringBuffer();
				while (iter.hasNext()) {
					ConfigAttribute attr = (ConfigAttribute) iter.next();
					sb.append(attr.getAttribute());
					sb.append(", ");
				}
				logger.debug("url = " + funtionItemWSVO.getUrl() + ", roles = " + sb.toString());
			}

		}

		// add definition source /login.jsf=IS_AUTHENTICATED_ANONYMOUSLY
		ArrayList<ConfigAttribute> ava = new ArrayList<ConfigAttribute>();
		ava.add(new SecurityConfig(AuthenticatedVoter.IS_AUTHENTICATED_ANONYMOUSLY));
		requestMap.put(new AntPathRequestMatcher("/login.jsf"), ava);
		// add definition source /**=IS_AUTHENTICATED_FULLY
		ArrayList<ConfigAttribute> aaf = new ArrayList<ConfigAttribute>();
		ava.add(new SecurityConfig(AuthenticatedVoter.IS_AUTHENTICATED_FULLY));
		requestMap.put(new AntPathRequestMatcher("/**/*.jsf"), aaf);

	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public Collection<ConfigAttribute> getConfigAttributeDefinitions() {
		return this.getAllConfigAttributes();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	

	public PortalClient getPortalClient() {
		return portalClient;
	}

	public void setPortalClient(PortalClient portalClient) {
		this.portalClient = portalClient;
	}

	public DefaultSetting getDefaultSetting() {
		return defaultSetting;
	}

	public void setDefaultSetting(DefaultSetting defaultSetting) {
		this.defaultSetting = defaultSetting;
	}

}
