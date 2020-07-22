package com.erez.thymeleaf.vehiclemodelsshope.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CrmSecurityConfig   extends WebSecurityConfigurerAdapter {

	
	
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/customers/showForm*").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/customers/save*").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/customers/delete").hasRole("ADMIN")
			.antMatchers("/customers/**").hasRole("EMPLOYEE")
			 .antMatchers("/addresses/showForm*").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/addresses/save*").hasAnyRole("MANAGER", "ADMIN")
			
			
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}

	
	
}
