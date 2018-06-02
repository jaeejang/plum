/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plum.config;

import org.hibernate.validator.HibernateValidator;
import org.plum.tools.runtime.MybatisXmlMapperAutoReloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@Import(SecurityConfig.class)
@EnableWebMvc
@ComponentScan(basePackages = { "org.plum.*"})
public class RootConfiguration {

	@Autowired
	private ApplicationContext applicationContext;
	

	@Bean
	public MybatisXmlMapperAutoReloader mybatisAutoReloader(){
		MybatisXmlMapperAutoReloader reloader = new MybatisXmlMapperAutoReloader();
		reloader.setMapperLocations("classpath*:org/plum/dao/*/*.xml");
		reloader.setEnableAutoReload(true);
		return reloader;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		config.setTemplateLoaderPath("classpath:tpl");
		config.setConfigLocation(new ClassPathResource("freemarker.properties"));
		return config;
	}
	

	@Bean
	public FreeMarkerViewResolver freeMarkerViewReSolverConfigure() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(false);
		resolver.setSuffix(".tpl");
		resolver.setPrefix("");
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setApplicationContext(applicationContext);
		resolver.setRequestContextAttribute("request");
		return resolver;
	}

	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setContentType("text/plain");
		view.setPrettyPrint(true);
		return view;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver bean = new CommonsMultipartResolver();
		bean.setDefaultEncoding("UTF-8");
		bean.setMaxUploadSize(8388608);
		return bean;
	}

	@Bean
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setProviderClass(HibernateValidator.class);
		validator.setValidationMessageSource(getMessageSource());
		return validator;
	}

	@Bean
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:validation.properties");
		source.setDefaultEncoding("UTF-8");
		return source;
	}
	
}