/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.domain.BlogEntry;
import io.swagger.messages.BlogResponse;
import io.swagger.repositories.BlogRepository;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.UserRepository;
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

public class BlogController implements BlogApi{
    @Autowired BlogRepository blogRepo;
    @Autowired CompanyRepository companyRepo;    
    @Autowired HttpServletRequest request;
    @Autowired UserRepository usersRepo;

    @Override
    public ResponseEntity<ArrayList<BlogResponse>> getBlogEntryByCompanyId(Long companyId) {
       
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.hasValidHeader() && validator.acceptsJson()) {
                 
                ArrayList<BlogEntry> blogEntries= blogRepo.findBlogEntryByCompanyId(companyId);
                if (blogEntries.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else 
                {   ArrayList<BlogResponse> blogResponse = new ArrayList<>();
                    
                    for(BlogEntry blogEntry:blogEntries){
                        BlogResponse responseEntry = new BlogResponse(
                                blogEntry.getId(),
                                blogEntry.getCompanyId(),
                                blogEntry.getTitle(),
                                blogEntry.getContent(),
                                blogEntry.getPostDate(),
                                blogEntry.getUserName()
                        );
                        blogResponse.add(responseEntry);
 
                    }
                    return new ResponseEntity<>(blogResponse,HttpStatus.OK);}
                                   
                } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);    
    }
    
}
