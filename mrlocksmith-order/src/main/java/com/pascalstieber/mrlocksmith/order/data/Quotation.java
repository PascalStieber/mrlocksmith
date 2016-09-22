package com.pascalstieber.mrlocksmith.order.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quotation extends ResourceSupport {

    @JsonProperty("id")
    private long quotationid;

    private boolean accepted = false;
    private Date acceptedAt;

    private long orderid;
    private long contractorid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotation", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("id DESC")
    private List<Item> items = new ArrayList<>();

    public void setItems(List<Item> items) {
	this.items = items;
    }

    public List<Item> getItems() {
	return items;
    }

    public void addItem(Item items) {
	this.items.add(items);
    }

    public void removeItem(Item pItem) {
	this.items.remove(pItem);
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

    public long getQuotationid() {
        return quotationid;
    }

    public void setQuotationid(long quotationid) {
        this.quotationid = quotationid;
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
