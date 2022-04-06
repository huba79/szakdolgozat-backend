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
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="SERVICES")
public class Service implements Serializable {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="LAKE_ID",columnDefinition="BIGINT NOT NULL")
    //@JoinColumn(name="LAKE_ID")
    Long lakeId;
    
    @Column(name="SERVICE_NAME",columnDefinition="VARCHAR(32) NOT NULL")
    String serviceName;

    @Column(name="UNIT_OF_MEASURE",columnDefinition="VARCHAR(16) NOT NULL")
    String unitOfMeasure;

    @Column(name="PRICE_PER_UNIT",columnDefinition="DOUBLE NOT NULL")
    Double pricePerUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLakeId() {
        return lakeId;
    }

    public void setLakeId(Long lakeId) {
        this.lakeId = lakeId;
    }
      
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    
    
}
