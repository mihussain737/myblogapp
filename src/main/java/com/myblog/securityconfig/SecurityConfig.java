package com.myblog.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		UserDetails normalUser = User.withUsername("Imam").password(passwordEncoder().encode("imam")).roles("USER")
				.build();

		UserDetails adminUser = User.withUsername("Imam1").password(passwordEncoder().encode("imam1")).roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(normalUser, adminUser);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
		.requestMatchers(HttpMethod.POST, "/api/posts/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/api/posts/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasRole("ADMIN")
		.anyRequest().authenticated().and()
		.httpBasic();

		return http.build();
	}

}
