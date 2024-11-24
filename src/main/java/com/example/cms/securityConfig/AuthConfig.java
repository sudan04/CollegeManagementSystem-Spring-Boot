package com.example.cms.securityConfig;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.cms.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class AuthConfig{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Bean
	public SecurityFilterChain securityFilterChaiin(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(req->req.requestMatchers("/homepage/**").permitAll()	
				.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.csrf(csrf->csrf.disable())
				.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		authenticationProvider.setUserDetailsService(myUserDetailsService);
		return authenticationProvider;
	}
}