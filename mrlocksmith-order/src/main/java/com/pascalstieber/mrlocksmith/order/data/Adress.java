package com.pascalstieber.mrlocksmith.order.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Adress extends ResourceSupport {

    @JsonProperty("id")
    private long adressid;
    private String postcode;
    private String country;
    private String street;
    private String streetnumber;
    private Set<User> user = new HashSet<User>();



    public long getAdressid() {
        return adressid;
    }

    public void setAdressid(long adressid) {
        this.adressid = adressid;
    }

    public Set<User> getUser() {
	return user;
    }

    public void addUser(User user) {
	this.user.add(user);
    }

    public String getStreet() {
	return street;
    }

    public String getPostcode() {
	return postcode;
    }

    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getStreetnumber() {
	return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
	this.streetnumber = streetnumber;
    }

}
