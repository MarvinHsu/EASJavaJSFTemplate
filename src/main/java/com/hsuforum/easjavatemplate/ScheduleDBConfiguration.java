package com.hsuforum.easjavatemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.quartz.autoconfigure.QuartzDataSource;
import org.springframework.boot.quartz.autoconfigure.QuartzTransactionManager;
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
  basePackages = {"com.hsuforum.eass.dao.schedule"},
  entityManagerFactoryRef = "scheduleEntityManagerFactory",
  transactionManagerRef = "scheduleTransactionManager"
)
public class ScheduleDBConfiguration {

	
	@Bean  
    @ConfigurationProperties(prefix = "spring.datasource.schedule")
    DataSourceProperties scheduleDataSourceProperties() {
        return new DataSourceProperties();
    }
 
	@Bean
	@QuartzDataSource
    DataSource scheduleDataSource(@Qualifier("scheduleDataSourceProperties")DataSourceProperties scheduleDataSourceProperties) {
        return scheduleDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
	
	@Bean  
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.schedule.jpa")
	JpaSetting scheduleJpaSetting() {
        return new JpaSetting();
    }
    @Bean
    LocalContainerEntityManagerFactoryBean scheduleEntityManagerFactory(@Qualifier("scheduleDataSource")DataSource scheduleDataSource, @Qualifier("scheduleJpaSetting")JpaSetting scheduleJpaSetting) {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(scheduleDataSource);
        emf.setPackagesToScan("com.hsuforum.eass.entity.schedule");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        
        Properties adjustProperties = new Properties();
        adjustProperties.put("hibernate.show_sql", scheduleJpaSetting.getShowSql());
        adjustProperties.put("hibernate.format_sql", scheduleJpaSetting.getFormatSql());
        adjustProperties.put("hibernate.event.merge.entity_copy_observer", scheduleJpaSetting.getEntityCopyObserver());
        adjustProperties.put("hibernate.dialect", scheduleJpaSetting.getDialect());
        adjustProperties.put("hibernate.physical_naming_strategy", scheduleJpaSetting.getPhysicalStrategy());
        emf.setJpaProperties(adjustProperties);
        return emf;
        
    }
    
    @Bean
    @QuartzTransactionManager
    PlatformTransactionManager scheduleTransactionManager(@Qualifier("scheduleEntityManagerFactory")EntityManagerFactory scheduleEntityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(scheduleEntityManagerFactory);

		return transactionManager;
	}

    @Bean
	TransactionInterceptor txAdviceSchedule(@Qualifier("scheduleTransactionManager") PlatformTransactionManager scheduleTransactionManager) {
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
		TransactionInterceptor txAdvice = new TransactionInterceptor(scheduleTransactionManager, source);
		return txAdvice;
	}
	@Bean
    Advisor dbOperation1Schedule(@Qualifier("txAdviceSchedule")TransactionInterceptor txAdviceSchedule) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Facade.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdviceSchedule);
    }
	@Bean
    Advisor dbOperation2Schedule(@Qualifier("txAdviceSchedule")TransactionInterceptor txAdviceSchedule) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Service.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdviceSchedule);
    }
}
