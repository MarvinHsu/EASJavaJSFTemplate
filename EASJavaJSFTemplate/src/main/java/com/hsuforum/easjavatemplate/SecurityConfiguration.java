package com.hsuforum.easjavatemplate;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

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
