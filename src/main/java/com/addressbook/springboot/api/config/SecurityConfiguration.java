package com.addressbook.springboot.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	/*
	 * Creating user based authentication with Different user having different
	 * roles
	 */
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("Bibash").password(encoder.encode("123")).roles("ADMIN").build();
		UserDetails normaluser = User.withUsername("Kattel").password(encoder.encode("456")).roles("USER").build();
/*
 * Users are hard coded and stored inMemory
 */
		return new InMemoryUserDetailsManager(admin, normaluser);

	}

	/*
	 * Defining HTTPsecurity for authorizing endpoints
	 * NOTE: Using HTTP basic authorization instead of formed based
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/api/v1/address-book/testroute", "/h2").permitAll().and().authorizeHttpRequests()
				.requestMatchers("/api/v1/address-book/**").authenticated().and().httpBasic().and().build();
	}

	/*
	 * Bean for encrypting password
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
