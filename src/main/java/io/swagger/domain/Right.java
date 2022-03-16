/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.io.Serializable;
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
@Table(name="RIGHTS")
public class Right implements Serializable {
    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
    private String rightName;
    private RightTypeEnum rightType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public RightTypeEnum getRightType() {
        return rightType;
    }

    public void setRightType(RightTypeEnum rightType) {
        this.rightType = rightType;
    }
    
    
    
}
