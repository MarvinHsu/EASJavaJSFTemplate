package com.hsuforum.easjavatemplate.security.userdetails;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hsuforum.easjavatemplate.DefaultSetting;
import com.hsuforum.easjavatemplate.ws.client.PortalClient;
import com.hsuforum.easjavatemplate.ws.vo.UserWSVO;

/**
 * 
 * @author Marvin
 *
 */
public class PortalUserDetailsService implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private PortalClient portalClient;
	@Autowired
	private DefaultSetting defaultSetting;
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		if (logger.isDebugEnabled()) {
			logger.debug("Load user by username: " + username);
		}

		if (username != null && !username.equals("")) {
			username = username.toUpperCase();
		} else {
			return null;
		}

		
		UserWSVO userWSVO=this.getPortalClient().findUserById(this.getDefaultSetting().getSystemId(), username);


		if (userWSVO == null) {
			throw new UsernameNotFoundException(
					this.messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }));
		}

		UserDetails userDetail = userWSVO;

		if (userDetail.getAuthorities().size() == 0) {
			throw new UsernameNotFoundException(
					this.messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] { username }));
		}

		return userWSVO;
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
