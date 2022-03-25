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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="PAYMENTS")
public class Payment implements Serializable {
    
    //TODO kell repo, kell response, service, controller
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    //@Column(name="RESERVATION_ID",columnDefinition="BIGINT NOT NULL")
    @OneToOne(mappedBy="payment")
    Reservation reservation;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="PAID_WITH",columnDefinition="VARCHAR(16) NOT NULL")
    PaidWithEnum paidWith;
    
    @Column(name="AMOUNT",columnDefinition="DOUBLE NOT NULL", length = 10, precision = 2)
    Double amount;
    
    @Column(name="PAID_ON",columnDefinition="DATETIME NOT NULL")
    Date paidOn;

    @Column(name="PAID_BY",columnDefinition="BIGINT NOT NULL")
    Long paidByUserId;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="PAYMENT_STATUS",columnDefinition="INTEGER NOT NULL")
    PaymentStatusEnum paymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaidWithEnum getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(PaidWithEnum paidWith) {
        this.paidWith = paidWith;
    }

    public Long getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(Long paidByUserId) {
        this.paidByUserId = paidByUserId;
    }
   
}
