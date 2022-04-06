/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.domain.PaidWithEnum;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author huba
 */
public class PaymentResponse {
    @JsonProperty("id") Long id;

//    @JsonManagedReference
//    @JsonProperty("reservation") Reservation reservation;
    
    @JsonProperty("reservationId") Long reservationId;
    
    @JsonProperty("paidWith") String paidWith;
    
    @JsonProperty("amount") Double amount;
    
    @JsonProperty("paidOn") Date paidOn;

    @JsonProperty("paidByUserid") String paidByUserId;
    
    @JsonProperty("paymentStatus") String paymentStatus; 

    public PaymentResponse(Long id, Long reservationId, String paidWith, 
            Double amount, Date paidOn, String paidByUserName, String paymentStatus) {
        this.id = id;
        this.reservationId = reservationId;
        this.paidWith = paidWith;
        this.amount = amount;
        this.paidOn = paidOn;
        this.paidByUserId = paidByUserName;
        this.paymentStatus = paymentStatus;
    }

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

    public String getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(String paidWith) {
        this.paidWith = paidWith;
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

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(String paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
}
