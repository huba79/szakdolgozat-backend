/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
/**
 *
 * @author huba
 */

@Entity
@Table(name="STAGES")
public class Stage implements Serializable {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;

    @Column(name="LAKE_ID",columnDefinition="BIGINT NOT NULL")
    Long lakeId;

    @Column(name="STAGE_NAME",columnDefinition="VARCHAR(16) NOT NULL")
    String stageName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="STAGE_TYPE",columnDefinition="INT UNSIGNED NOT NULL")
    StageTypesEnum stageType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="STAGE_STATUS",columnDefinition="INT UNSIGNED NOT NULL")
    StageStatusEnum stageStatus;
    
    @Column(name="STAGE_SIZE",columnDefinition="INT UNSIGNED NOT NULL")
    Long stageSize;

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

    public StageTypesEnum getStageType() {
        return stageType;
    }

    public void setStageType(StageTypesEnum stageType) {
        this.stageType = stageType;
    }

    public Long getStageSize() {
        return stageSize;
    }

    public void setStageSize(Long stageSize) {
        this.stageSize = stageSize;
    }

    public StageStatusEnum getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatusEnum stageStatus) {
        this.stageStatus = stageStatus;
    }    
    
}
