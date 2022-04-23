package com.grigor.pancakes_unlimited;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("customer").password("osd").roles("customer")
		.and().withUser("employee").password("palacinke").roles("employee")
		.and().withUser("owner").password("store").roles("owner")
		.and().passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/pancakes_unlimited.com/API/ingredients/**").hasRole("employee")
		.antMatchers("/pancakes_unlimited.com/API/pancakes/**").hasRole("customer")
		.antMatchers("/pancakes_unlimited.com/API/orders/**").hasRole("customer")
		.antMatchers("/pancakes_unlimited.com/API/reports").hasRole("owner")
		.and().httpBasic();
		http.csrf().disable();
	}
	
	
}