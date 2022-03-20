/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.domain.BlogEntry;
import io.swagger.messages.BlogResponse;
import io.swagger.repositories.BlogRepository;
import io.swagger.repositories.CompanyRepository;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huba
 */
@RestController
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")

public class BlogController implements BlogService{
    @Autowired
    BlogRepository blogRepo;
    @Autowired
    CompanyRepository companyRepo;    
    @Autowired
    HttpServletRequest request;


    @Override
    public ResponseEntity<BlogResponse> getBlogEntryByCompanyId(Long companyId) {
        //apikey validaciot egysegesiteni kellene, , talan ez a jobb megkozelites         
        if( request.getHeader("X-API-KEY") !=null 
            && request.getHeader("X-API-KEY").equals(companyRepo.findById(companyId).get().getApiKey()) && 
                "application/json".equals(request.getHeader("Accepts"))) {
                 
                ArrayList<BlogEntry> blogEntries= blogRepo.findBlogEntryByCompanyId(companyId);
                if (blogEntries.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else return new ResponseEntity<>(new BlogResponse(blogEntries),HttpStatus.OK);
                                   
                } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);    
    }
    
}
