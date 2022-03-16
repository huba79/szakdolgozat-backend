/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

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
@Table(name="LAKES")
public class Lake implements Serializable {
    
    //@JsonProperty("id")
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
        
    @Column(name="LAKE_NAME",columnDefinition="VARCHAR(32) NOT NULL")
    String lakeName;

    
    @Column(name="COMPANY_ID",columnDefinition="BIGINT NOT NULL")
    Long companyId;

    @Column(name="LAKE_ADDRESS",columnDefinition="VARCHAR(128) NOT NULL")
    String lakeAddress;

    @Column(name="RESERVATION_SYSTEM",columnDefinition="VARCHAR(16) NOT NULL")
    String reservationsSystem;

    @Column(name="LAKE_SIZE",columnDefinition="DOUBLE NOT NULL",length=3, precision=2)
    Double lakeSize;

    public Lake(String lakeName, Long companyId, String lakeAddress, String reservationsSystem, Double lakeSize) {
        this.lakeName = lakeName;
        this.companyId = companyId;
        this.lakeAddress = lakeAddress;
        this.reservationsSystem = reservationsSystem;
        this.lakeSize = lakeSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLakeName() {
        return lakeName;
    }

    public void setLakeName(String lakeName) {
        this.lakeName = lakeName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getLakeAddress() {
        return lakeAddress;
    }

    public void setLakeAddress(String lakeAddress) {
        this.lakeAddress = lakeAddress;
    }

    public String getReservationsSystem() {
        return reservationsSystem;
    }

    public void setReservationsSystem(String reservationsSystem) {
        this.reservationsSystem = reservationsSystem;
    }

    public Double getLakeSize() {
        return lakeSize;
    }

    public void setLakeSize(Double lakeSize) {
        this.lakeSize = lakeSize;
    }
    
    
}
