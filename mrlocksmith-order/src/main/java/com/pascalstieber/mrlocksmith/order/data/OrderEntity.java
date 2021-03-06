package com.pascalstieber.mrlocksmith.order.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

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
    private long adressid;
    
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

    public long getAdressid(){
	return adressid;
    }
    
    public void setAdressid(long adressid) {
	this.adressid = adressid;
	
    }

}
