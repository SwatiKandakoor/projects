package com.xworkz.cm.configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sun.tools.sjavac.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebIni extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("getRootConfigClasses");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("getServletConfigClasses");
		return new Class[] {SignUpConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		log.info("getServletMappings");	
		return new String[] { "/" };
	}
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		log.info("configureDefaultServletHandling");
		configurer.enable();
	}

}
