/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="COMPANIES")
public class Company {
    //@JsonProperty("id")
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
  
    ////@JsonProperty("companyName")
    String companyName;

    //@JsonProperty("officialName")
    String officialName;

    //@JsonProperty("regNr")
    String regNr;

    //@JsonProperty("companyAddress")
    String companyAddress;

    //@JsonProperty("ibanAddress")
    String ibanAddress;

    //@JsonProperty("apiKey")
    String apiKey;

    //@JsonProperty("contactName")
    String contactName;

    //@JsonProperty("contactPhone")
    String contactPhone;

    //@JsonProperty("contactEmail")
    String contactEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getIbanAddress() {
        return ibanAddress;
    }

    public void setIbanAddress(String ibanAddress) {
        this.ibanAddress = ibanAddress;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
