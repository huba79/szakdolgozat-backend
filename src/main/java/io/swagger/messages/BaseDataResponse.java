/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.domain.*;
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
    ArrayList<Price> prices;
    
    @JsonProperty("stages")    
    ArrayList<Stage> stages;


    public BaseDataResponse(Company company, ArrayList<Lake> lakes, ArrayList<Price> prices, ArrayList<Stage> stages) {

        this.company = company;
        this.lakes = lakes;
        this.prices = prices;
        this.stages = stages;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
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
    
    
}
