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
@Table(name="SERVICES_AND_PRICES")
public class ServicePrice implements Serializable {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="PRODUCT_NAME",columnDefinition="VARCHAR(32) NOT NULL")
    String productName;

    @Column(name="LAKE_ADDRESS",columnDefinition="VARCHAR(128) NOT NULL")
    String unitOfMeasure;

    @Column(name="PRICE_UNIT",columnDefinition="DOUBLE NOT NULL")
    Double pricePerUnit;

//    public ServicePrice(Long id, String productName, String unitOfMeasure, Double pricePerUnit) {
//        this.id = id;
//        this.productName = productName;
//        this.unitOfMeasure = unitOfMeasure;
//        this.pricePerUnit = pricePerUnit;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String serviceName) {
        this.productName = serviceName;
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
