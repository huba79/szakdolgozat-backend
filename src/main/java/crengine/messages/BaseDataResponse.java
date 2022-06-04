/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.messages;

import crengine.domain.RuleOfConduct;
import crengine.domain.Service;
import crengine.domain.Stage;
import crengine.domain.Company;
import crengine.domain.Lake;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author huba
 */
public class BaseDataResponse {
    
    @JsonProperty("company")
    Company company;
    
    @JsonProperty("lakes")
    ArrayList<Lake> lakes;
    
    @JsonProperty("prices")
    ArrayList<Service> prices;
    
    @JsonProperty("stages")    
    ArrayList<Stage> stages;

    @JsonProperty("rules")    
    ArrayList<RuleOfConduct> rules;

    public BaseDataResponse(Company company, ArrayList<Lake> lakes, ArrayList<Service> prices, ArrayList<Stage> stages,ArrayList<RuleOfConduct> rules) {

        this.company = company;
        this.lakes = lakes;
        this.prices = prices;
        this.stages = stages;
        this.rules = rules;
    }

    public ArrayList<Service> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Service> prices) {
        this.prices = prices;
    }    
    
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Lake> getLakes() {
        return lakes;
    }

    public void setLakes(ArrayList<Lake> lakes) {
        this.lakes = lakes;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public ArrayList<RuleOfConduct> getRules() {
        return rules;
    }

    public void setRules(ArrayList<RuleOfConduct> rules) {
        this.rules = rules;
    }
    
    
}
