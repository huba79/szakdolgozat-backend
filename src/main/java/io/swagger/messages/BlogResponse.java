/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.configuration.Configuration;
import java.util.Date;

/**
 *
 * @author huba
 */
public class BlogResponse  {
        
    @JsonProperty("entryId")
    Long id;
    
    @JsonProperty("companyId")
    Long companyId;
    
    @JsonProperty("title")
    String title;
    
    @JsonProperty("content")
    String content;
    
    @JsonProperty("postedOn")
    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone=Configuration.TIMEZONE)
    Date postDate;
    
    @JsonProperty("userId")
    Long userId;
    
    @JsonProperty("userName")
    String userName;

    public BlogResponse(Long id, Long companyId, String title, String content, Date postDate, Long userId, String userName) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.userId = userId;
        this.userName = userName;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }  
    
}
