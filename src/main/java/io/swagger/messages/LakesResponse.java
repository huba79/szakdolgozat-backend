/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huba
 */
public class LakesResponse {
        
    @JsonProperty("lakes")
    private List<LakeResponse> lakes;

    public LakesResponse(List<LakeResponse> lakes) {
        this.lakes = lakes;
    }

    public List<LakeResponse> getLakes() {
        return lakes;
    }

    public void setLakes(List<LakeResponse> lakes) {
        this.lakes = lakes;
    }
    
    
}
