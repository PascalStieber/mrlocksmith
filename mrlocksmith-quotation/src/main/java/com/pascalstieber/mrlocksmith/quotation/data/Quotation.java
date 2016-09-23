package com.pascalstieber.mrlocksmith.quotation.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pascalstieber.mrlocksmith.quotation.web.QuotationController;

@Entity
public class Quotation {

    @Id
    @GeneratedValue
    private long id;
    @Transient
    private final Logger log = LoggerFactory.getLogger(QuotationController.class);
    private boolean accepted = false;
    private Date acceptedAt;

    private long orderid;
    private long contractorid;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    @OrderBy("id DESC")
    @JoinColumn(name = "quotation")
    private List<Item> items = new ArrayList<>();


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item items) {
	this.items.add(items);
    }

    public boolean isAccepted() {
	return accepted;
    }

    public void setAccepted(boolean accepted) {
	this.accepted = accepted;
    }

    public Date getAcceptedAt() {
	return acceptedAt;
    }

    public void setAcceptedAt(Date acceptedAt) {
	this.acceptedAt = acceptedAt;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public long getOrderid() {
	return orderid;
    }

    public void setOrderid(long orderid) {
	this.orderid = orderid;
    }

    public long getContractorid() {
	return contractorid;
    }

    public void setContractorid(long contractorid) {
	this.contractorid = contractorid;
    }


}
