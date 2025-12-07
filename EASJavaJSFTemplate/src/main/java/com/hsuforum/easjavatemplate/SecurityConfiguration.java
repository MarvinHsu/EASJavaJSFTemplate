package com.hsuforum.easjavatemplate;

import org.apereo.cas.client.session.SingleSignOutFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfiguration {

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

	SecurityConfiguration(CasAuthenticationEntryPoint casAuthenticationEntryPoint, LogoutFilter logoutFilter,
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
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		
		http.authorizeHttpRequests((requests) -> {
			try {
				requests
					.requestMatchers("/images/**").permitAll()
					.requestMatchers("/resources/**").permitAll()
					.requestMatchers("/img/**").permitAll()
					.requestMatchers("/*.html").permitAll()
					.requestMatchers("/*.xml").permitAll()
					.requestMatchers("/*.txt").permitAll()
					.requestMatchers("/login.jsf").permitAll()
					.requestMatchers("/jakarta.faces.resource/**").permitAll()
					.requestMatchers("/index.jspx").permitAll()
					.requestMatchers("/default.jsf").permitAll()
					.requestMatchers("/favicon.ico").permitAll()
					.requestMatchers("/error").permitAll()
					.requestMatchers("/exception/exception.jsf").permitAll()
					.requestMatchers("/rest/**").permitAll()
					.requestMatchers("/").permitAll()
					.anyRequest().authenticated();
					
			} catch (Exception e) {

				e.printStackTrace();
			}
		}).csrf(csrf -> csrf
			.disable()
		).headers(header -> header
				.frameOptions( frameOption -> frameOption
					.disable()
			)
		).exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(casAuthenticationEntryPoint))
		.addFilterBefore(concurrentSessionFilter, ConcurrentSessionFilter.class)
		.addFilterBefore(securityContextPersistenceFilter, SecurityContextPersistenceFilter.class)
		.addFilterBefore(casAuthenticationFilter, CasAuthenticationFilter.class)
		.addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class)
		.addFilterBefore(logoutFilter, LogoutFilter.class)
		.addFilterBefore(securityContextHolderAwareRequestFilter, SecurityContextHolderAwareRequestFilter.class)
		.addFilterBefore(anonymousProcessingFilter, AnonymousAuthenticationFilter.class)
		.addFilterBefore(exceptionTranslationFilter, ExceptionTranslationFilter.class)
		.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);;
		
		return http.build();
	}


}
