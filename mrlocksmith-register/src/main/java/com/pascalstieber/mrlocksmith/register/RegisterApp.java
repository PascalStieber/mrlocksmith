package com.pascalstieber.mrlocksmith.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
public class RegisterApp {

    public static void main(String[] args) {
	SpringApplication.run(RegisterApp.class, args);
    }
    
    
}
