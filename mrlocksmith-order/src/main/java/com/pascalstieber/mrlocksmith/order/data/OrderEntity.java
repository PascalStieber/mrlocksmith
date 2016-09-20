package com.pascalstieber.mrlocksmith.order.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private long id;

    private boolean keyNotAvailable = true;
    private String homeOrCar = "garage";
    private String door = "wooden";
    private boolean express = false;

    private long userid;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch =
    // FetchType.LAZY)
    // @OrderBy("id DESC")
    // private Set<Offer> offers = new HashSet<>();
    //
    // public Set<Offer> getOffers() {
    // return offers;
    // }
    //
    // public void addOffer(Offer offer) {
    // this.offers.add(offer);
    // }
    //
    public long getUserid() {
	return userid;
    }

    public void setUserid(long userid) {
	this.userid = userid;
    }

    public boolean isKeyNotAvailable() {
	return keyNotAvailable;
    }

    public void setKeyNotAvailable(boolean keyNotAvailable) {
	this.keyNotAvailable = keyNotAvailable;
    }

    public String getHomeOrCar() {
	return homeOrCar;
    }

    public void setHomeOrCar(String homeOrCar) {
	this.homeOrCar = homeOrCar;
    }

    public String getDoor() {
	return door;
    }

    public void setDoor(String door) {
	this.door = door;
    }

    public boolean isExpress() {
	return express;
    }

    public void setExpress(boolean express) {
	this.express = express;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

}
