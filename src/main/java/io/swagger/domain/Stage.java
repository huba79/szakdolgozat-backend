/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name="STAGES")
public class Stage {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    //@JsonProperty("lakeId")
    Long lakeId;

    //@JsonProperty("stageName")
    String stageName;

    //@JsonProperty("stageType")
    String stageType;

    //@JsonProperty("stageSize")
    Long stageSize;

    //@JsonProperty("stageStatus")
    Long stageStatus;

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

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public Long getStageSize() {
        return stageSize;
    }

    public void setStageSize(Long stageSize) {
        this.stageSize = stageSize;
    }

    public Long getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(Long stageStatus) {
        this.stageStatus = stageStatus;
    }
    
    
}
