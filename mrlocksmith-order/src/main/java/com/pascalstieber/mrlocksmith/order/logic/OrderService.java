package com.pascalstieber.mrlocksmith.order.logic;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pascalstieber.mrlocksmith.order.clients.QuotationClient;
import com.pascalstieber.mrlocksmith.order.data.Item;

@Service
public class OrderService {

    private QuotationClient quotationClient;

    @Autowired
    private OrderService(QuotationClient quotationClient) {
	this.quotationClient = quotationClient;
    }
    
    public String getQuotationSum(long quotationid){
	return quotationClient.getQuotationSum(quotationid);
    }
    
}
