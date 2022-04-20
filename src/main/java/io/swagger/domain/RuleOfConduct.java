/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.io.Serializable;
import java.util.Date;
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
public class RuleOfConduct implements Serializable {

    private @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    
    @Column(name="COMPANY_ID", columnDefinition= "BIGINT NOT NULL",length=10)
    Long companyId;
   
    @Column(name="lake_id",columnDefinition="BIGINT NOT NULL")
    Long lakeId ;  
    
    @Column(name="RULE_TEXT",columnDefinition="VARCHAR(1024) NOT NULL")
    String ruleText;  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getLakeId() {
        return lakeId;
    }

    public void setLakeId(Long lakeId) {
        this.lakeId = lakeId;
    }

    public String getRuleText() {
        return ruleText;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
    }       
    
}
