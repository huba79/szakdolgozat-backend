/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.configuration.Configuration;


/**
 *
 * @author huba
 */
public class RulesResponse {
    
    @JsonProperty("companyId") Long companyId;
    
    @JsonProperty("lakeId") Long lakeId;
    
    @JsonProperty("ruleText") String RuleText;

    public RulesResponse(Long companyId, Long lakeId, String RuleText) {
        this.companyId = companyId;
        this.lakeId = lakeId;
        this.RuleText = RuleText;
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
        return RuleText;
    }

    public void setRuleText(String RuleText) {
        this.RuleText = RuleText;
    }    

}
