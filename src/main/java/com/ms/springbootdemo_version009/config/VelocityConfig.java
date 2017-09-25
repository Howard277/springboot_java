package com.ms.springbootdemo_version009.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 * 视图模板velocity的配置
 * 
 * @author wuketao
 *
 */
@Configuration
public class VelocityConfig {

	@Bean
	public VelocityViewResolver getVelocityViewResolver() {
		VelocityViewResolver resolver = new VelocityViewResolver();
		resolver.setSuffix(".html");
		resolver.setContentType("text/html;charset=utf-8");
		return resolver;
	}
}
