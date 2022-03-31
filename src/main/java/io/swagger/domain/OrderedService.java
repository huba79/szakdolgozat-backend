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
@Table(name="ORDERED_SERVICES")
public class OrderedService implements Serializable {
    
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="RESERVATION_ID",columnDefinition="BIGINT NOT NULL")
    //@JoinColumn(name="RESERVATION_ID")
    Long reservationId;
    
    @Column(name="SERVICE_ID",columnDefinition="BIGINT NOT NULL")
    String serviceId;
    
    @Column(name="AMOUNT_ORDERED",columnDefinition="DOUBLE NOT NULL")
    Double amountOrdered;
    
    @Column(name="PRICE",columnDefinition="DOUBLE NOT NULL")
    Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Double getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(Double amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }    
    
}
