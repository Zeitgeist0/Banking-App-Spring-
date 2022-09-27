package com.bankingappfinal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig  {
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    System.out.println("filterChain");
//    http
//      .csrf().disable()
//      .authorizeRequests()
//      .antMatchers("/login", "/base", "/console/**").permitAll()
//      .antMatchers("/customers/**", "/accounts/**","/employers/**").hasRole("USER")
//      .antMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
//      .and()
//      .formLogin()
//      .loginPage("/login")
//      .permitAll()
//      .and()
//      .logout()
//      .logoutSuccessUrl("/login")
//      .invalidateHttpSession(true)
//      .deleteCookies("JSESSIONID")
//      .permitAll();
//    return http.build();
//  }
//}