/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

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
@Table(name="SERVICES_AND_PRICES")
public class ServicePrice {
    
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="SERVICE_NAME",columnDefinition="VARCHAR2 NOT NULL", length=32)
    String serviceName;

    @Column(name="UNIT_OF_MEASURE",columnDefinition="VARCHAR2 NOT NULL", length=16)
    String unitOfMeasure;

    @Column(name="PRICE_PER_UNIT",columnDefinition="DOUBLE NOT NULL", length=10, precision=2)
    Double pricePerUnit;

//    public ServicePrice(Long id, String serviceName, String unitOfMeasure, Double pricePerUnit) {
//        this.id = id;
//        this.serviceName = serviceName;
//        this.unitOfMeasure = unitOfMeasure;
//        this.pricePerUnit = pricePerUnit;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
