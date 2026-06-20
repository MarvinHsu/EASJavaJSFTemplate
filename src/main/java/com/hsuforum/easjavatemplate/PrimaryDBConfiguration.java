package com.hsuforum.easjavatemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"com.hsuforum.easjavatemplate.dao.primary"},
  entityManagerFactoryRef = "primaryEntityManagerFactory",
  transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDBConfiguration {
	
	
	@Bean  
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }
	
 
	@Bean
    @Primary
    DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties")DataSourceProperties primaryDataSourceProperties) {
        return primaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    
	@Bean  
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary.jpa")
	JpaSetting primaryJpaSetting() {
        return new JpaSetting();
    }
    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(@Qualifier("primaryDataSource")DataSource primaryDataSource, @Qualifier("primaryJpaSetting")JpaSetting primaryJpaSetting) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(primaryDataSource);
        emf.setPackagesToScan("com.hsuforum.easjavatemplate.entity.primary");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        
        Properties adjustProperties = new Properties();
        adjustProperties.put("hibernate.show_sql", primaryJpaSetting.getShowSql());
        adjustProperties.put("hibernate.format_sql", primaryJpaSetting.getFormatSql());
        adjustProperties.put("hibernate.event.merge.entity_copy_observer", primaryJpaSetting.getEntityCopyObserver());
        adjustProperties.put("hibernate.dialect", primaryJpaSetting.getDialect());
        adjustProperties.put("hibernate.physical_naming_strategy", primaryJpaSetting.getPhysicalStrategy());
        emf.setJpaProperties(adjustProperties);
        return emf;
        
    }
	
	@Bean(name="primaryTransactionManager")
	PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory")EntityManagerFactory primaryEntityManagerFactory) {
		
		return new JpaTransactionManager(Objects.requireNonNull(primaryEntityManagerFactory));
	}

	@Bean(name="txAdvicePrimary")
	TransactionInterceptor txAdvicePrimary(@Qualifier("primaryTransactionManager")PlatformTransactionManager primaryTransactionManager) {
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
		TransactionInterceptor txAdvice = new TransactionInterceptor(primaryTransactionManager, source);
		return txAdvice;
	}
	@Bean
    Advisor dbOperation1Primary(@Qualifier("txAdvicePrimary")TransactionInterceptor txAdvicePrimary) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Facade.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvicePrimary);
    }
	@Bean
    Advisor dbOperation2Primary(@Qualifier("txAdvicePrimary")TransactionInterceptor txAdvicePrimary) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Service.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvicePrimary);
    }
	
}
