package com.hsuforum.easjavatemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas30ProxyTicketValidator;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.hsuforum.easjavatemplate.security.intercept.web.PortalFilterInvocationDefinitionSource;
import com.hsuforum.easjavatemplate.security.userdetails.PortalUserDetailsService;
import com.hsuforum.easjavatemplate.security.vote.UserVoter;
import com.hsuforum.easjavatemplate.web.config.WSConfigBean;
import com.hsuforum.easjavatemplate.ws.client.PortalClient;
//@EnableCasClient
@Configuration
@ImportResource(value = { "classpath*:ScheduleContext.xml",
		"classpath*:WebContext.xml", "classpath*:ServiceContext.xml", "classpath*:DaoContext.xml",
		"classpath*:DBContext.xml" })
public class ProjectConfiguration {
	
	
    
	@Bean
	@ConfigurationProperties(prefix = "project")
	public DefaultSetting defaultSetting() {
		return new DefaultSetting();
	}
    @Bean 
    public PortalClient portalClient(DefaultSetting defaultSetting) {
    	PortalClient portalClient = new PortalClient();
    	portalClient.setPortalWSURI(defaultSetting.getPortalWSURI());
    	return portalClient;
    }

    @Bean
    public WSConfigBean wsConfigBean(DefaultSetting defaultSetting) {
    	WSConfigBean wsConfigBean = new WSConfigBean();
    	wsConfigBean.setDataTableRows(defaultSetting.getDataTableRows());
    	wsConfigBean.setMailFrom(defaultSetting.getMailFrom());
    	return wsConfigBean;
    }

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}

	@Bean
	public TransactionInterceptor txAdvice(PlatformTransactionManager transactionManager) {
		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		
		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
		readOnlyTx.setReadOnly(true);
		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		
		RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
		requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		Map<String, TransactionAttribute> txMap = new HashMap<>();
		txMap.put("create*", requiredTx);
		txMap.put("update*", requiredTx);
		txMap.put("delete*", requiredTx);
		txMap.put("*", readOnlyTx);
		source.setNameMap(txMap);
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
		return txAdvice;
	}
	@Bean
    public Advisor dbOperation1(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Facade.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
	@Bean
    public Advisor dbOperation2(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Service.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
	
	@Bean
    public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
    }
	
	@Bean
    public ConcurrentSessionControlAuthenticationStrategy sessionController(SessionRegistry sessionRegistry) {
		ConcurrentSessionControlAuthenticationStrategy sessionController= new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		sessionController.setMaximumSessions(1);
		return sessionController;
    }
	@Bean
    public ConcurrentSessionFilter concurrentSessionFilter(SessionRegistry sessionRegistry) {
		ConcurrentSessionFilter concurrentSessionFilter= new ConcurrentSessionFilter(sessionRegistry);
		return concurrentSessionFilter;
    }
	@Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        return new SecurityContextPersistenceFilter();
    }
	@Bean
    public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		
	    singleSignOutFilter.setLogoutCallbackPath("/exit/cas");
	    singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }
	@Bean
    public LogoutFilter logoutFilter() {
	
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		LogoutFilter LogoutFilter = new LogoutFilter("https://localhost:9443/cas/logout", securityContextLogoutHandler);
		LogoutFilter.setFilterProcessesUrl("/logout/cas");
        return LogoutFilter;
    }
	@Bean
    public UserDetailsService userDetailsService() {
		
		PortalUserDetailsService portalUserDetailsService = new PortalUserDetailsService();
		
        return portalUserDetailsService;
    }
	@Bean
	public Cas30ProxyTicketValidator cas30ProxyTicketValidator() {
		Cas30ProxyTicketValidator cas30ProxyTicketValidator =new Cas30ProxyTicketValidator("https://localhost:9443/cas");
		cas30ProxyTicketValidator.setAcceptAnyProxy(true);
		return cas30ProxyTicketValidator;
	}
	@Bean
	public CasAuthenticationProvider casAuthenticationProvider(UserDetailsService userDetailsService, ServiceProperties serviceProperties, Cas30ProxyTicketValidator cas30ProxyTicketValidator) {
		
		CasAuthenticationProvider casAuthenticationProvider= new CasAuthenticationProvider();
		casAuthenticationProvider.setUserDetailsService(userDetailsService);
		casAuthenticationProvider.setServiceProperties(serviceProperties);
	    casAuthenticationProvider.setTicketValidator(cas30ProxyTicketValidator);
	    casAuthenticationProvider.setKey("CAS_PROVIDER_LOCALHOST_8900");
	
        return casAuthenticationProvider;
    }
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter(
		AuthenticationManager authenticationManager,
	    ServiceProperties serviceProperties) throws Exception {
	    CasAuthenticationFilter filter = new CasAuthenticationFilter();
	    filter.setAuthenticationManager(authenticationManager);
	    filter.setServiceProperties(serviceProperties);
	    return filter;
	}
	@Bean
    public AuthenticationManager authenticationManager(CasAuthenticationProvider casAuthenticationProvider) {
		
		List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
		authenticationProviders.add(casAuthenticationProvider);
		AuthenticationManager authenticationManager =new ProviderManager(authenticationProviders);
        return authenticationManager;
    }
	
	@Bean
    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        return new SecurityContextHolderAwareRequestFilter();
    }
	@Bean
    public AnonymousAuthenticationFilter anonymousProcessingFilter() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(simpleGrantedAuthority);
		AnonymousAuthenticationFilter anonymousProcessingFilter = new AnonymousAuthenticationFilter("foobar", "anonymousUser", authorities);	
        return anonymousProcessingFilter;
    }

	@Bean
    public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService("https://localhost:6443/EASJavaJSFTemplate/login/cas");
		serviceProperties.setSendRenew(false);
		serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }
	@Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		loginUrlAuthenticationEntryPoint.setLoginUrl("https://localhost:9443/cas/login");
		loginUrlAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return loginUrlAuthenticationEntryPoint;
    }
	
	@Bean
    public ExceptionTranslationFilter exceptionTranslationFilter(CasAuthenticationEntryPoint loginUrlAuthenticationEntryPoint) {
		ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(loginUrlAuthenticationEntryPoint);	
        return exceptionTranslationFilter;
    }
	
	@Bean
    public AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		decisionVoters.add(new UserVoter());
		decisionVoters.add(new AuthenticatedVoter());
		AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
		
        return affirmativeBased;
    }

	@Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(PortalClient portalClient, DefaultSetting defaultSetting) {
		PortalFilterInvocationDefinitionSource portalFilterInvocationDefinitionSource = new PortalFilterInvocationDefinitionSource();
		portalFilterInvocationDefinitionSource.setPortalClient(portalClient);
		portalFilterInvocationDefinitionSource.setDefaultSetting(defaultSetting);
		portalFilterInvocationDefinitionSource.init();
        return portalFilterInvocationDefinitionSource;
    }
	
	@Bean
    public FilterSecurityInterceptor filterSecurityInterceptor(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource, AuthenticationManager authenticationManager, AffirmativeBased accessDecisionManager) {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
		filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
		
        return filterSecurityInterceptor;
    }
	@Bean
    public LoggerListener loggerListener() {
        return new LoggerListener();
    }
	@EventListener
	public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event){
		return new SingleSignOutHttpSessionListener();
		
	}
}
