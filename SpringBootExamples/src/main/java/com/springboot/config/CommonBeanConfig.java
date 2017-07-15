package com.springboot.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.springboot.repositories")
@Configuration
public class CommonBeanConfig {

	@Bean
	public StrongPasswordEncryptor passwordEncryptor() {
		return new StrongPasswordEncryptor();
	}
	
}
