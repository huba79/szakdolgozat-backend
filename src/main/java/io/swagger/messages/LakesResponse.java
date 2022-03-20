/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author huba
 */
public class LakesResponse {
        
    @JsonProperty("lakes")
    private ArrayList<LakeResponse> lakes;

    public LakesResponse(ArrayList<LakeResponse> lakes) {
        this.lakes = lakes;
    }

    public ArrayList<LakeResponse> getLakes() {
        return lakes;
    }

    public void setLakes(ArrayList<LakeResponse> lakes) {
        this.lakes = lakes;
    }
    
    
}
