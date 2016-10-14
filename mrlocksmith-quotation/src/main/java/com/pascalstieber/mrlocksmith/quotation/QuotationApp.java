package com.pascalstieber.mrlocksmith.quotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableOAuth2Sso
public class QuotationApp extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(QuotationApp.class);
    
    @Autowired
    OAuth2ClientContext oAuth2ClientContext;
  
    public static void main(String[] args) {
	SpringApplication.run(QuotationApp.class, args);
    }

   
    @Override
    public void configure(HttpSecurity http) throws Exception {
	
	AuthenticationEntryPoint aep = new AuthenticationEntryPoint() {
	    
	    @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response,
		    org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
		response.sendRedirect("http://192.168.99.100:8080/quotation/login");
	    }
	};
	
	http.antMatcher("/**").authorizeRequests().antMatchers("/index.html", "/home.html", "/", "/login").permitAll().anyRequest().authenticated().
	and().formLogin().permitAll().
	and().exceptionHandling().authenticationEntryPoint(aep).
	and().csrf().disable();
    }
    
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT email,password,enabled from user where email=?")
//		.authoritiesByUsernameQuery("select email,role from user where email=?");
//
//    }
}