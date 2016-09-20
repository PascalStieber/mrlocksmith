package com.pascalstieber.mrlocksmith.register.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Vorname darf nicht leer sein!")
    @Size(min = 2, max = 20, message = "Der Vorname muss min. 3 und darf max. 20 Zeichen lang sein.")
    private String firstname;
    @NotEmpty(message = "Nachname darf nicht leer sein!")
    @Size(min = 2, max = 30, message = "Der Nachname muss min. 3 und darf max. 30 Zeichen lang sein.")
    private String surname;

    private long orderid;

    @NotEmpty(message = "Email darf nicht leer sein!")
    @Email
    private String email;
    @NotEmpty(message = "Telefonnummer darf nicht leer sein!")
    private String phonenumber;

    @NotEmpty(message = "Passwort darf nicht leer sein!")
    private String password;
    private String role = "customer";

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Adress.class)
    @JoinTable(name = "User_Adress")
    private List<Adress> adresses = new ArrayList<>();

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch =
    // FetchType.LAZY)
    // private Set<OrderEntity> orders = new HashSet<>();
    //

    //
    // public Set<OrderEntity> getOrders() {
    // return orders;
    // }
    //
    // public void addOrder(OrderEntity order) {
    // this.orders.add(order);
    // }
    //

    public List<Adress> getAdresses() {
	return adresses;
    }

    public void addAdress(Adress adress) {
	this.adresses.add(adress);
    }

    public void setAdresses(List<Adress> adresses) {
	this.adresses = adresses;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhonenumber() {
	return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public long getOrderid() {
	return orderid;
    }

    public void setOrderid(long orderid) {
	this.orderid = orderid;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this);

    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj);
    }
}
