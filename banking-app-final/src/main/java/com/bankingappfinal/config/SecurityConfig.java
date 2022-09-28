package com.bankingappfinal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins("http://localhost:3000")
          .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
        .allowedHeaders("Content_Type", "Authorization");
      }
    };
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    System.out.println("filterChain");
    http

      .csrf().and().cors().disable()
      .authorizeRequests()
      .antMatchers("/login/**", "/base", "/h2-console/**").permitAll()
      .antMatchers("/customers/**", "/accounts/**","/employers/**" ).hasAuthority("USER")
      .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
      .and()
      .formLogin()
//      .successForwardUrl("/")
      .permitAll()
      .and()
      .logout()
      .logoutSuccessUrl("/login")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
      .permitAll();
    return http.build();
  }
}