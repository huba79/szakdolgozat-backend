/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
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
@Table(name= "USERS")
public class User implements Serializable {
    
   private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
    
    @Column(name="DISPLAY_NAME",columnDefinition="VARCHAR(64) NOT NULL")
    String displayName;

    @Column(name="NICK_NAME",columnDefinition="VARCHAR(16) NOT NULL")
    String nickName;

    @Column(name="EMAIL",columnDefinition="VARCHAR(128) NOT NULL")
    String emailAddress;
    
    @Column(name="PASSWORD",columnDefinition="VARCHAR(16) NOT NULL")
    String password;

    @Column(name="GROUP_NAME",columnDefinition="VARCHAR(16) NOT NULL")
    String groupName;

    @Column(name="COMPANY_ID",columnDefinition="BIGINT NOT NULL")
    Long companyId;

    @Column(name="SESSION_ID",columnDefinition="VARCHAR(64)")
    String sessionID;

    //@JsonProperty("createdDate")
    Date createdDate;

    //@JsonProperty("lastLoginDate")
    Date lastLoginDate;
    
    private static final Logger LOG = Logger.getLogger(User.class.getName());    
    

    public Long getId() {
        return id;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    @Column(name="EMAIL_ADDRESS", nullable=false, length=64)
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    
    
}
