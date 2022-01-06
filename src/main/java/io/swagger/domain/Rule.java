/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.annotation.Generated;
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
@Table(name="RULES_OF_CONDUCT")
public class Rule {
 

    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
    
    @Column(name="LAKE_ID",columnDefinition="BIGINT NOT NULL")
    Long lakeid;

    @Column(name="RULE_NR",columnDefinition="BIGINT NOT NULL")
    Long ruleNr;

    @Column(name="RULE_TEXT",columnDefinition="VARCHAR2 NOT NULL", length=1024)
    String ruleText;

    @Column(name="ACTIVE_FROM",columnDefinition="DATETIME NOT NULL")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleNr() {
        return ruleNr;
    }

    public void setRuleNr(Long ruleNr) {
        this.ruleNr = ruleNr;
    }

    public String getRuleText() {
        return ruleText;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
    }


    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }
    
    
}
