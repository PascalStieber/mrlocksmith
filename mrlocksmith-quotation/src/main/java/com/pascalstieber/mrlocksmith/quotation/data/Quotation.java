package com.pascalstieber.mrlocksmith.quotation.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Quotation {

    @Id
    @GeneratedValue
    private long id;

    private boolean accepted = false;
    private Date acceptedAt;

    private long orderid;
    private long contractorid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotation", fetch = FetchType.EAGER)
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
