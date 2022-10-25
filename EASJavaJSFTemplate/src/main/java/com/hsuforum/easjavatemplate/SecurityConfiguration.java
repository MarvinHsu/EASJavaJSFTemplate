package com.hsuforum.easjavatemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas30ProxyTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.hsuforum.easjavatemplate.security.intercept.web.PortalFilterInvocationDefinitionSource;
import com.hsuforum.easjavatemplate.security.userdetails.PortalUserDetailsService;
import com.hsuforum.easjavatemplate.security.vote.UserVoter;
import com.hsuforum.easjavatemplate.ws.client.PortalClient;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	CasAuthenticationEntryPoint casAuthenticationEntryPoint;

	LogoutFilter logoutFilter;

	CasAuthenticationProvider casAuthenticationProvider;
	AuthenticationManager authenticationManager;

	SingleSignOutFilter singleSignOutFilter;
	ConcurrentSessionFilter concurrentSessionFilter;
	SecurityContextPersistenceFilter securityContextPersistenceFilter;
	CasAuthenticationFilter casAuthenticationFilter;
	SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter;
	ExceptionTranslationFilter exceptionTranslationFilter;
	FilterSecurityInterceptor filterSecurityInterceptor;
	AnonymousAuthenticationFilter anonymousProcessingFilter;


	public SecurityConfiguration(CasAuthenticationEntryPoint casAuthenticationEntryPoint, LogoutFilter logoutFilter,
			CasAuthenticationProvider casAuthenticationProvider, AuthenticationManager authenticationManager,
			SingleSignOutFilter singleSignOutFilter, ConcurrentSessionFilter concurrentSessionFilter,
			SecurityContextPersistenceFilter securityContextPersistenceFilter,
			CasAuthenticationFilter casAuthenticationFilter,
			SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter,
			ExceptionTranslationFilter exceptionTranslationFilter, FilterSecurityInterceptor filterSecurityInterceptor,
			AnonymousAuthenticationFilter anonymousProcessingFilter) {
		super();
		this.casAuthenticationEntryPoint = casAuthenticationEntryPoint;
		this.logoutFilter = logoutFilter;
		this.casAuthenticationProvider = casAuthenticationProvider;
		this.authenticationManager = authenticationManager;
		this.singleSignOutFilter = singleSignOutFilter;
		this.concurrentSessionFilter = concurrentSessionFilter;
		this.securityContextPersistenceFilter = securityContextPersistenceFilter;
		this.casAuthenticationFilter = casAuthenticationFilter;
		this.securityContextHolderAwareRequestFilter = securityContextHolderAwareRequestFilter;
		this.exceptionTranslationFilter = exceptionTranslationFilter;
		this.filterSecurityInterceptor = filterSecurityInterceptor;
		this.anonymousProcessingFilter = anonymousProcessingFilter;
	}


	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
        		.authorizeRequests();

        registry.antMatchers("/images/**").permitAll();
        registry.antMatchers("/resources/**").permitAll();
        registry.antMatchers("/img/**").permitAll();
        registry.antMatchers("/*.html").permitAll();
        registry.antMatchers("/*.xml").permitAll();    
   
        registry
        	.and()        
        	.csrf().disable()
        	.headers().frameOptions().disable()
        	.and()
        	.authorizeRequests().anyRequest().authenticated() 
        	.and()
        	.exceptionHandling()
	        .authenticationEntryPoint(casAuthenticationEntryPoint)
	        .and()
            .addFilterBefore(concurrentSessionFilter, ConcurrentSessionFilter.class)
            .addFilterBefore(securityContextPersistenceFilter, SecurityContextPersistenceFilter.class)
            .addFilterBefore(casAuthenticationFilter, CasAuthenticationFilter.class)
            .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class)
            .addFilterBefore(logoutFilter, LogoutFilter.class)
            .addFilterBefore(securityContextHolderAwareRequestFilter, SecurityContextHolderAwareRequestFilter.class)
            .addFilterBefore(anonymousProcessingFilter, AnonymousAuthenticationFilter.class)
            .addFilterBefore(exceptionTranslationFilter, ExceptionTranslationFilter.class)
            .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
            
        
    }
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers( "/kernel/*").authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint)
        .and()
        .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class)
        .addFilterBefore(logoutFilter, LogoutFilter.class)
        .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
        .csrf().disable()
        .headers().frameOptions().disable();
	}*/

	

}
