package com.pascalstieber.mrlocksmith.quotation.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    private Quotation quotation;

    @NotEmpty(message="Der Posten muss gekennzeichnet und darf nicht leer sein!")
    private String name;
    @Pattern(regexp="^[0-9]*[.][0-9][0-9]", message="Der Betrag muss als Dezimalzahl angegeben werden: 00.00")
    private String value;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Quotation getQuotation() {
	return quotation;
    }

    public void setQuotation(Quotation quotation) {
	this.quotation = quotation;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
