/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.annotation.Generated;
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
@Table(name="RULES_OF_CONDUCT")
public class Rule {
 
//@JsonProperty("id")
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
    
    //@JsonProperty("lakeId")
    Long lakeid;

    //@JsonProperty("ruleTitle")
    String serviceName;

    //@JsonProperty("ruleText")
    String unitOfMeasure;

    //@JsonProperty("activeFrom")
    Date activeFrom;

//    public Rule(Long lakeid, String serviceName, String unitOfMeasure, Date activeFrom) {
//        this.lakeid = lakeid;
//        this.serviceName = serviceName;
//        this.unitOfMeasure = unitOfMeasure;
//        this.activeFrom = activeFrom;
//    }

    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public Long getLakeid() {
        return lakeid;
    }

    public void setLakeid(Long lakeid) {
        this.lakeid = lakeid;
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

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }
    
    
}
