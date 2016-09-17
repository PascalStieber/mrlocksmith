package com.pascalstieber.mrlocksmith.quotation.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Quotation {

    @Id
    @GeneratedValue
    private long id;
    
    private boolean accepted = false;
    private Date acceptedAt;
    
    // @ManyToOne
    // private Order order;
    //
    // @ManyToOne
    // private Contractor contractor;
    //
    // public Contractor getContractor() {
    // return contractor;
    // }
    //
    // public void setContractor(Contractor contractor) {
    // this.contractor = contractor;
    // }
    //
    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer", fetch =
    // FetchType.LAZY, orphanRemoval = true)
    // @OrderBy("id DESC")
    // private List<Item> items = new ArrayList<>();
    //

    //
    // public Order getOrder() {
    // return order;
    // }
    //
    // public void setOrder(Order order) {
    // this.order = order;
    // }
    //
    // public List<Item> getItems() {
    // return items;
    // }
    //
    // public void addItem(Item items) {
    // this.items.add(items);
    // }
    //
    // public void removeItem(Item pItem) {
    // this.items.remove(pItem);
    // }

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
    
}
