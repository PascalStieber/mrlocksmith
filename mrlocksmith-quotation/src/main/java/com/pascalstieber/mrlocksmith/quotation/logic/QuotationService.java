package com.pascalstieber.mrlocksmith.quotation.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pascalstieber.mrlocksmith.quotation.clients.OrderClient;
import com.pascalstieber.mrlocksmith.quotation.clients.RegisterClient;
import com.pascalstieber.mrlocksmith.quotation.data.Adress;
import com.pascalstieber.mrlocksmith.quotation.data.OrderEntity;
import com.pascalstieber.mrlocksmith.quotation.data.Quotation;
import com.pascalstieber.mrlocksmith.quotation.data.QuotationRepository;
import com.pascalstieber.mrlocksmith.quotation.data.User;

@Service
public class QuotationService {

    private QuotationRepository quotationRepository;
    private RegisterClient registerClient;
    private OrderClient orderClient;

    @Autowired
    private QuotationService(QuotationRepository quotationRepository, RegisterClient registerClient, OrderClient orderClient) {
	this.quotationRepository = quotationRepository;
	this.registerClient = registerClient;
	this.orderClient = orderClient;
    }

    public Boolean allreadyTendered(long orderid, long contractorid) {
	if (quotationRepository.findByOrderidAndContractorid(orderid, contractorid).isEmpty()) {
	    return false;
	}
	return true;
    }

    public Boolean tenderCancelOrEditable(String orderid, String contractorid){
	long contractor = Long.parseLong(contractorid);
	long order = Long.parseLong(orderid);
	List<Quotation> quotation = quotationRepository.findByOrderidAndContractorid(order, contractor);
	if(quotation.isEmpty()){
	    return false;
	}else if(quotation.get(0).isAccepted() != false){
	    return false;
	}
	return true;
    }

    public Boolean isTenderAccepted(long orderid, long contractorid){
	List<Quotation> quotation = quotationRepository.findByOrderidAndContractorid(orderid, contractorid);
	if(quotation.isEmpty()){
	    return false;
	}else if(quotation.get(0).isAccepted() == true){
	    return true;
	}
	return false;
    }
    
    public OrderEntity getOrderById(long orderid) {
	OrderEntity order = orderClient.getOrderByOrderid(orderid);
	return order;
    }

    public User getUserById(long userid) {
	User user = registerClient.getUser(userid);
	return user;
    }

    public Adress getOrderAdressByOrderid(long orderid) {
	Adress adress = registerClient.getAdressById(orderid);
	return adress;
    }

    public User getUserByOrderid(long orderid) {
	OrderEntity order = orderClient.getOrderByOrderid(orderid);
	User user = registerClient.getUser(order.getUserid());
	return user;
    }

}
