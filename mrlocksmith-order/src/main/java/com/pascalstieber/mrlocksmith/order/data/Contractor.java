package com.pascalstieber.mrlocksmith.order.data;

import org.hibernate.validator.constraints.NotEmpty;

public class Contractor extends User {

    @NotEmpty(message = "Firmenname darf nicht leer sein!")
    private String companyname;
    @NotEmpty(message = "Vorname des Geschäftsführers darf nicht leer sein!")
    private String holderfirstname;
    @NotEmpty(message = "Nachname des Geschäftsführers darf nicht leer sein!")
    private String holdersurname;

    public String getCompanyname() {
	return companyname;
    }

    public void setCompanyname(String companyname) {
	this.companyname = companyname;
    }

    public String getHolderfirstname() {
	return holderfirstname;
    }

    public void setHolderfirstname(String holderfirstname) {
	this.holderfirstname = holderfirstname;
    }

    public String getHoldersurname() {
	return holdersurname;
    }

    public void setHoldersurname(String holdersurname) {
	this.holdersurname = holdersurname;
    }

}
