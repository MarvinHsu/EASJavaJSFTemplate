package com.hsuforum.easjavatemplate;

import java.util.ArrayList;
import java.util.List;

import org.apereo.cas.client.boot.configuration.EnableCasClient;
import org.apereo.cas.client.session.SingleSignOutFilter;
import org.apereo.cas.client.session.SingleSignOutHttpSessionListener;
import org.apereo.cas.client.validation.Cas30ProxyTicketValidator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.hsuforum.common.service.util.ServiceLocator;
import com.hsuforum.easjavatemplate.exception.ExceptionHandler;
import com.hsuforum.easjavatemplate.security.intercept.web.PortalFilterInvocationDefinitionSource;
import com.hsuforum.easjavatemplate.security.userdetails.PortalUserDetailsService;
import com.hsuforum.easjavatemplate.security.vote.UserVoter;
import com.hsuforum.easjavatemplate.ws.client.PortalClient;

import jakarta.servlet.http.HttpSessionEvent;


@EnableCasClient
@Configuration
@ImportResource(value = { "classpath*:WebContext.xml"  })
public class ProjectConfiguration {
	
	@Bean
	@ConfigurationProperties(prefix = "project")
	DefaultSetting defaultSetting() {
		return new DefaultSetting();
	}
	@Bean
	ExceptionHandler exceptionHandler() {
		return new ExceptionHandler();
	}
	@Bean
	ServiceLocator serviceLocator() {
		return new ServiceLocator();
	}
	
	@Bean
    SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
    }
	
	@Bean
    ConcurrentSessionControlAuthenticationStrategy sessionController(SessionRegistry sessionRegistry) {
		ConcurrentSessionControlAuthenticationStrategy sessionController= new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		sessionController.setMaximumSessions(1);
		return sessionController;
    }
	@Bean
    ConcurrentSessionFilter concurrentSessionFilter(SessionRegistry sessionRegistry) {
		ConcurrentSessionFilter concurrentSessionFilter= new ConcurrentSessionFilter(sessionRegistry);
		return concurrentSessionFilter;
    }
	@Bean
    SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        return new SecurityContextPersistenceFilter();
    }
	@Bean
    SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		
	    singleSignOutFilter.setLogoutCallbackPath("/exit/cas");
	    singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }
	@Bean
    LogoutFilter logoutFilter() {
	
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		LogoutFilter LogoutFilter = new LogoutFilter("https://localhost:9443/cas/logout", securityContextLogoutHandler);
		LogoutFilter.setFilterProcessesUrl("/logout/cas");
        return LogoutFilter;
    }
	@Bean
    UserDetailsService userDetailsService() {
		
		PortalUserDetailsService portalUserDetailsService = new PortalUserDetailsService();
		
        return portalUserDetailsService;
    }
	@Bean
	Cas30ProxyTicketValidator cas30ProxyTicketValidator() {
		Cas30ProxyTicketValidator cas30ProxyTicketValidator =new Cas30ProxyTicketValidator("https://localhost:9443/cas");
		cas30ProxyTicketValidator.setAcceptAnyProxy(true);
		return cas30ProxyTicketValidator;
	}
	@Bean
	CasAuthenticationProvider casAuthenticationProvider(UserDetailsService userDetailsService, ServiceProperties serviceProperties, Cas30ProxyTicketValidator cas30ProxyTicketValidator) {
		
		CasAuthenticationProvider casAuthenticationProvider= new CasAuthenticationProvider();
		casAuthenticationProvider.setUserDetailsService(userDetailsService);
		casAuthenticationProvider.setServiceProperties(serviceProperties);
	    casAuthenticationProvider.setTicketValidator(cas30ProxyTicketValidator);
	    casAuthenticationProvider.setKey("CAS_PROVIDER_LOCALHOST_8900");
	
        return casAuthenticationProvider;
    }
	@Bean
	@Primary
	CasAuthenticationFilter casAuthenticationFilter(
		AuthenticationManager authenticationManager,
	    ServiceProperties serviceProperties) throws Exception {
	    CasAuthenticationFilter filter = new CasAuthenticationFilter();
	    filter.setAuthenticationManager(authenticationManager);
	    filter.setServiceProperties(serviceProperties);
	    return filter;
	}
	@Bean
    AuthenticationManager authenticationManager(CasAuthenticationProvider casAuthenticationProvider) {
		
		List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
		authenticationProviders.add(casAuthenticationProvider);
		AuthenticationManager authenticationManager =new ProviderManager(authenticationProviders);
        return authenticationManager;
    }
	
	@Bean
    SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        return new SecurityContextHolderAwareRequestFilter();
    }
	@Bean
    AnonymousAuthenticationFilter anonymousProcessingFilter() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(simpleGrantedAuthority);
		AnonymousAuthenticationFilter anonymousProcessingFilter = new AnonymousAuthenticationFilter("foobar", "anonymousUser", authorities);	
        return anonymousProcessingFilter;
    }

	@Bean
    ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService("https://localhost:10443/EASJavaJSFTemplate/login/cas");
		serviceProperties.setSendRenew(false);
		serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }
	@Bean
    CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		loginUrlAuthenticationEntryPoint.setLoginUrl("https://localhost:9443/cas/login");
		loginUrlAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return loginUrlAuthenticationEntryPoint;
    }
	
	@Bean
    ExceptionTranslationFilter exceptionTranslationFilter(CasAuthenticationEntryPoint loginUrlAuthenticationEntryPoint) {
		ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(loginUrlAuthenticationEntryPoint);	
        return exceptionTranslationFilter;
    }
	
	@Bean
    AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		decisionVoters.add(new UserVoter());
		decisionVoters.add(new AuthenticatedVoter());
		AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
		
        return affirmativeBased;
    }

	@Bean
    FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(PortalClient portalClient, DefaultSetting defaultSetting) {
		PortalFilterInvocationDefinitionSource portalFilterInvocationDefinitionSource = new PortalFilterInvocationDefinitionSource();
		portalFilterInvocationDefinitionSource.setPortalClient(portalClient);
		portalFilterInvocationDefinitionSource.setDefaultSetting(defaultSetting);
		portalFilterInvocationDefinitionSource.init();
        return portalFilterInvocationDefinitionSource;
    }
	
	@Bean
    FilterSecurityInterceptor filterSecurityInterceptor(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource, AuthenticationManager authenticationManager, AffirmativeBased accessDecisionManager) {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
		filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
		
        return filterSecurityInterceptor;
    }
	@Bean
    LoggerListener loggerListener() {
        return new LoggerListener();
    }
	@EventListener
	SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event){
		return new SingleSignOutHttpSessionListener();
		
	}	

}
