package com.pascalstieber.mrlocksmith.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableOAuth2Sso
public class OrderApp extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext oauth2Context;

    public static void main(String[] args) {
	SpringApplication.run(OrderApp.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//	http.antMatcher("/showCustomersQuotations.html").authorizeRequests().antMatchers("/index.html", "/home.html", "/").permitAll().anyRequest().authenticated();
	http.antMatcher("/**").authorizeRequests().
	antMatchers("/","/questionnaire1Form.html","/questionnaireExpress","/img/**").permitAll().
	anyRequest().authenticated().
	and().formLogin().defaultSuccessUrl("/acceptQuotation.html").permitAll().
	and().csrf().disable();
    }
}
