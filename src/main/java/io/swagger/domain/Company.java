/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
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
public class Company implements Serializable {
    
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
  
    @Column(name="COMPANY_NAME",columnDefinition="VARCHAR(64) NOT NULL")
    String companyName;

    @Column(name="OFFICIAL_NAME",columnDefinition="VARCHAR(64) NOT NULL")
    String officialName;

    @Column(name="REG_NR",columnDefinition="VARCHAR(16) NOT NULL")
    String regNr;

    @Column(name="COMPANY_ADDRESS",columnDefinition="VARCHAR(128) NOT NULL")
    String companyAddress;

    @Column(name="IBAN_ADDRESS",columnDefinition="VARCHAR(24) NOT NULL")
    String ibanAddress;

    @Column(name="API_KEY",columnDefinition="VARCHAR(24) NOT NULL")
    String apiKey;

    @Column(name="CONTACT_NAME",columnDefinition="VARCHAR(64) NOT NULL")
    String contactName;

    @Column(name="CONTACT_PHONE",columnDefinition="VARCHAR(16)")
    String contactPhone;

    @Column(name="COMPANY_EMAIL",columnDefinition="VARCHAR(64) NOT NULL")
    String contactEmail;
    
    @Column(name="DEAFULT_CURRENCY",columnDefinition="VARCHAR(3) NOT NULL")
    String defaultCurrency;

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
        
    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
}
