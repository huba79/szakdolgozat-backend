/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.domain.Service;
import io.swagger.domain.Stage;
import java.util.ArrayList;
import java.util.List;

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
    
    @JsonManagedReference
    @JsonProperty("stages")
    List<Stage> stages;
    
    @JsonManagedReference
    @JsonProperty("services")
    List<Service> services;

    public LakeResponse(Long id, String lakeName, Long companyId, 
            String lakeAddress, String reservationsSystem, Double lakeSize,
            List<Stage> stages,List<Service> services) {
        this.id = id;
        this.lakeName = lakeName;
        this.companyId = companyId;
        this.lakeAddress = lakeAddress;
        this.reservationsSystem = reservationsSystem;
        this.lakeSize = lakeSize;
        this.services = services;
        this.stages = stages;
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

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
}
