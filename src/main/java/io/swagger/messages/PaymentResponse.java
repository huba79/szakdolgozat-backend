/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.domain.PaidWithEnum;
import io.swagger.domain.PaymentStatusEnum;
import io.swagger.domain.Reservation;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author huba
 */
public class PaymentResponse {
    @JsonProperty("id") Long id;

    @JsonManagedReference
    @JsonProperty("reservation") Reservation reservation;
    
    @Enumerated(EnumType.STRING)
    @JsonProperty("paidWith") PaidWithEnum paidWith;
    
    @JsonProperty("amount") Double amount;
    
    @JsonProperty("paidOn") Date paidOn;

    @JsonProperty("paidByUserName") String paidByUserName;
    
    @Enumerated(EnumType.STRING)
    @JsonProperty("paymentStatus") PaymentStatusEnum paymentStatus; 

    public PaymentResponse(Long id, Reservation reservation, PaidWithEnum paidWith, 
            Double amount, Date paidOn, String paidByUserName, PaymentStatusEnum paymentStatus) {
        this.id = id;
        this.reservation = reservation;
        this.paidWith = paidWith;
        this.amount = amount;
        this.paidOn = paidOn;
        this.paidByUserName = paidByUserName;
        this.paymentStatus = paymentStatus;
    }
    
    
}
