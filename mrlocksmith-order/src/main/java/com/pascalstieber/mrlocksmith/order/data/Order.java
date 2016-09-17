package com.pascalstieber.mrlocksmith.order.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private long id;

    private boolean keyNotAvailable = false;
    private String homeOrCar = "home";
    private String door = "normal";
    private boolean express;

    // @ManyToOne
    // private User user;
    //
    // @ManyToOne
    // private Adress adress;
    //
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
    // public User getUser() {
    // return user;
    // }
    //
    // public void setUser(User user) {
    // this.user = user;
    // }
    //
    // public Adress getAdress() {
    // return adress;
    // }
    //
    // public void setAdress(Adress adress) {
    // this.adress = adress;
    // }

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
