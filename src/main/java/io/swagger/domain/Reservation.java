/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author huba
 */
@Entity
@Table(name="RESERVATIONS")
public class Reservation {

    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
    
    @Column(name="LAKE_ID",columnDefinition="BIGINT NOT NULL")
    Long lakeId;

    @Column(name="STAGE_ID",columnDefinition="BIGINT NOT NULL")
    Long stageId;
    
    @Column(name="USER_ID",columnDefinition="BIGINT NOT NULL")
    Long userId;

    @Column(name="DATE_FROM",columnDefinition="DATETIME NOT NULL")
    Date dateFrom;
    
    //ide kellhet egy after insert trigger hogy szamolja a date_to-t
    @Column(name="DURATION_HR",columnDefinition="DATETIME NOT NULL")
    Integer durationInHr;

    @OneToMany
    //TODO UTANANEZNI!!!!!!@!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    List<ServicePrice> requestedServices;

    @Column(name="PRICE",columnDefinition="DOUBLE NOT NULL", precision=2)
    Double price;

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

    public Integer getDurationInHr() {
        return durationInHr;
    }

    public void setDurationInHr(Integer durationInHr) {
        this.durationInHr = durationInHr;
    }

    public List<ServicePrice> getRequestedServices() {
        return requestedServices;
    }

    public void setRequestedServices(List<ServicePrice> requestedServices) {
        this.requestedServices = requestedServices;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateTo(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFrom);
        calendar.add(Calendar.HOUR_OF_DAY, durationInHr);
        return calendar.getTime();
  
    }
    
}
