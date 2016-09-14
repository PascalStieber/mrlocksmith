package com.pascalstieber.mrlocksmith.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
public class IndexApp {
    
    public static void main(String[] args) {
	SpringApplication.run(IndexApp.class, args);
    }
}
