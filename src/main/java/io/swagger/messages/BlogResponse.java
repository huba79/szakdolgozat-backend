/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.domain.BlogEntry;
import java.util.ArrayList;

/**
 *
 * @author huba
 */
public class BlogResponse  {
        
    @JsonProperty("blog")
    ArrayList<BlogEntry> blog;

    public BlogResponse(ArrayList<BlogEntry> blog) {
        this.blog = blog;
    }

    public ArrayList<BlogEntry> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<BlogEntry> blog) {
        this.blog = blog;
    }
    
    
  
    
}
