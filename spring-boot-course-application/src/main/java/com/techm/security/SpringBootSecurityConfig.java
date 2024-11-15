package com.techm.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringBootSecurityConfig {

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		// Below line make request as stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		UserDetails user1 = User.withUsername("user").password("{noop}userpass").roles("USER").build();
		UserDetails admin = User.withUsername("admin").password("{noop}adminpass").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user1, admin);

	}

}
