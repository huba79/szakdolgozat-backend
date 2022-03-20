/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author huba
 */
public class LakeResponse  {
        
    @JsonProperty("id")
    private Long id;
        
    @JsonProperty("lakename")
    String lakeName;

    
    @JsonProperty("companyId")
    Long companyId;

    @JsonProperty("lakeAddress")
    String lakeAddress;

    @JsonProperty("reservationsSystem")
    String reservationsSystem;

    @JsonProperty("lakeSize")
    Double lakeSize;

    public LakeResponse(Long id, String lakeName, Long companyId, String lakeAddress, String reservationsSystem, Double lakeSize) {
        this.id = id;
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
