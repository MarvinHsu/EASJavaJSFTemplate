package com.hsuforum.easjavatemplate;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageRedirect implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/kernel/default.jsf");
		registry.addViewController("/error").setViewName("forward:/exception/exception.jsf");

		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}