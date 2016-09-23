package com.pascalstieber.mrlocksmith.quotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.pascalstieber.mrlocksmith.quotation.data.Item;
import com.pascalstieber.mrlocksmith.quotation.data.ItemRepository;
import com.pascalstieber.mrlocksmith.quotation.data.Quotation;
import com.pascalstieber.mrlocksmith.quotation.data.QuotationRepository;

@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@Component
public class QuotationApp {

    public static void main(String[] args) {
	SpringApplication.run(QuotationApp.class, args);
    }
}
