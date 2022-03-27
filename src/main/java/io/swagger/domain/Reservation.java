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
@Table(name="RESERVATIONS")
public class Reservation implements Serializable {

    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="LAKE_ID",columnDefinition="BIGINT NOT NULL")
    Long lakeId;

    @Column(name="STAGE_ID",columnDefinition="BIGINT NOT NULL")
    Long stageId;

    @Column(name="USER_ID",columnDefinition="BIGINT NOT NULL")
    Long userId;

    @Column(name="DATE_FROM",columnDefinition="DATETIME NOT NULL")
    Date dateFrom;
    
    @Column(name="DATE_TO",columnDefinition="DATETIME NOT NULL")
    Date dateTo;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="RESERVATION_STATUS",columnDefinition="VARCHAR(12) NOT NULL")
    ReservationStatusEnum reservationStatus;

    public Reservation(Long id, Long lakeId, Long stageId, Long userId, Date dateFrom, Date dateTo, ReservationStatusEnum reservationStatus) {
        this.id = id;
        this.lakeId = lakeId;
        this.stageId = stageId;
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.reservationStatus = reservationStatus;
    }
    

 

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

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo(){
        return dateTo;  
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public ReservationStatusEnum getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatusEnum reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
     
}
