package com.pascalstieber.mrlocksmith.register.data;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Testentity  extends ResourceSupport {

    @JsonProperty("id")
    private long testId;

    private String local;

    
    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
