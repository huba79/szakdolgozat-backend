/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import crengine.configuration.Configuration;
import crengine.domain.OrderedItem;
import crengine.domain.Payment;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author huba
 */
public class ReservationResponse implements Serializable{
    @JsonProperty("reservationId")
    Long reservationId;
    
    @JsonProperty("LakeId")
    Long lakeId;
    
    @JsonProperty("userId")
    Long userId;
    
    @JsonProperty("stageId")
    Long stageId;
    
    @JsonProperty("dateFrom")
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone=Configuration.TIMEZONE)         
    Date dateFrom;
    
    @JsonProperty("dateTo")
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone=Configuration.TIMEZONE)     
    Date dateTo;
    
    @JsonProperty("status")
    String status;
    
    @JsonProperty("orderedItems")
    List<OrderedItem> orderedItems;
    
    @JsonProperty("payment")
    Payment payment;
    
    @JsonProperty("userName")
    String userName;

    public ReservationResponse(Long reservationId, Long lakeId,  Long stageId, Long userId, Date dateFrom, Date dateTo, String status, List<OrderedItem> orderedItems, Payment payment, String userName) {
        this.reservationId = reservationId;
        this.lakeId = lakeId;
        this.userId = userId;
        this.stageId = stageId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
        this.orderedItems = orderedItems;
        this.payment = payment;
        this.userName = userName;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getLakeId() {
        return lakeId;
    }

    public void setLakeId(Long lakeId) {
        this.lakeId = lakeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

        
    
}
