/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.api;

import crengine.domain.BlogEntry;
import crengine.messages.BlogResponse;
import crengine.repositories.BlogRepository;
import crengine.repositories.CompanyRepository;
import crengine.repositories.UserRepository;
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
        if(  validator.isApiKeyValid() && validator.acceptsJson()) {
                 
            try {
                ArrayList<BlogEntry> blogEntries= blogRepo.findBlogEntryByCompanyId(companyId);
                if (blogEntries.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {   
                    ArrayList<BlogResponse> blogResponses = new ArrayList<>();
                    for(BlogEntry blogEntry:blogEntries){
                        BlogResponse responseEntry = new BlogResponse(
                                blogEntry.getId(),
                                blogEntry.getCompanyId(),
                                blogEntry.getTitle(),
                                blogEntry.getContent(),
                                blogEntry.getPostDate(),
                                blogEntry.getUserId(),
                                ( usersRepo.findById(blogEntry.getUserId()) ).get().getDisplayName()
                        );
                        blogResponses.add(responseEntry);
                    }                     
                        return new ResponseEntity<>(blogResponses,HttpStatus.OK);
                    } 
            }catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            }
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
