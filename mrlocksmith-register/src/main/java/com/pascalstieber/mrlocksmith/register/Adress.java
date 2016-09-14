package com.pascalstieber.mrlocksmith.register;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Adress {

    @Id
    @GeneratedValue
    private long id;
    
    @NotEmpty(message = "PLZ darf nicht leer sein!")
    @DecimalMax(value = "999999")
    @DecimalMin(value = "1111")
    private String postcode;

    @NotEmpty(message = "Ort darf nicht leer sein!")
    private String country;

    @NotEmpty(message = "Straße darf nicht leer sein!")
    private String street;

    @NotEmpty(message = "Hausnr. ist ein erforderliches Feld")
    private String streetnumber;

    @ManyToMany(mappedBy = "adresses")
    private Set<User> user = new HashSet<User>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
