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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
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
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		
		http.authorizeHttpRequests((requests) -> {
			try {
				requests
					.requestMatchers(mvcMatcherBuilder.pattern("/images/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/resources/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/img/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/*.html")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/*.xml")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/*.txt")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/login.jsf")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/jakarta.faces.resource/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/index.jspx")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/default.jsf")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/favicon.ico")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/exception/exception.jsf)")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
					.and()
					.csrf().disable()
					.headers().frameOptions().disable()
					.and()
					.authorizeHttpRequests().anyRequest().authenticated()
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
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		
		return http.build();
	}


}
