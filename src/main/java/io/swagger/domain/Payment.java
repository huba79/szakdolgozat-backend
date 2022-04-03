/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="PAYMENTS")
public class Payment implements Serializable {
    
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="RESERVATION_ID",columnDefinition="BIGINT NOT NULL")
    Long reservationId;
    
    @Column(name="PAID_WITH",columnDefinition="VARCHAR(16) NOT NULL")
    String paidWith;
    
    @Column(name="AMOUNT",columnDefinition="DOUBLE NOT NULL", length = 10, precision = 2)
    Double amount;
    
    @Column(name="PAID_ON",columnDefinition="DATETIME NOT NULL")
    Date paidOn;

    @Column(name="PAID_BY",columnDefinition="BIGINT NOT NULL")
    Long paidByUserId;
    
    @Column(name="PAYMENT_STATUS",columnDefinition="INTEGER NOT NULL")
    String paymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(Date paidOn) {
        this.paidOn = paidOn;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(String paidWith) {
        this.paidWith = paidWith;
    }

    public Long getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(Long paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
    
   
}
