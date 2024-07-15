package com.example.customermanagementsystem.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.customermanagementsystem.user.Permission;

@Configuration
@EnableWebSecurity
public class SecurityFilter {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
          .csrf(csrfConfig -> csrfConfig.disable())
          .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authenticationProvider(authenticationProvider)
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
          .authorizeHttpRequests( authConfig -> {
              authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
              authConfig.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
              authConfig.requestMatchers(HttpMethod.GET, "/api/customers/{id}").permitAll();
              authConfig.requestMatchers(HttpMethod.POST, "/api/customers").permitAll();
              authConfig.requestMatchers(HttpMethod.PUT, "/api/customers/{id}").permitAll();
              authConfig.requestMatchers(HttpMethod.DELETE, "/api/customers/{id}").permitAll();
              authConfig.requestMatchers("/error").permitAll();
              authConfig.requestMatchers("/").permitAll();

              authConfig.requestMatchers(HttpMethod.GET, "/api/customers/{id}").hasAuthority(Permission.READ_ALL_CUSTOMERS.name());
              authConfig.requestMatchers(HttpMethod.POST, "/api/customers").hasAuthority(Permission.SAVE_ONE_CUSTOMER.name());
              authConfig.requestMatchers(HttpMethod.PUT, "/api/customers/{id}").hasAuthority(Permission.SAVE_ONE_CUSTOMER.name());

              authConfig.requestMatchers(HttpMethod.DELETE, "/api/customers/{id}").hasAuthority(Permission.READ_ALL_CUSTOMERS.name());
//              authConfig.anyRequest().denyAll();


          });

  return http.build();
  

}

}
