package com.pascalstieber.mrlocksmith.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
public class OrderApp  {

    public static void main(String[] args) {
	SpringApplication.run(OrderApp.class, args);
    }


}
