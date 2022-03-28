/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.configuration.Configuration;
import io.swagger.domain.Company;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author huba
 */

public class RequestValidator {
    @Autowired     
    Environment env;
    
    private final HttpServletRequest request;
    private final CompanyRepository companyRepo;
    private final UserRepository userRepo;
    
    public RequestValidator(  HttpServletRequest request, UserRepository userRepo, CompanyRepository companyRepo) {
        this.request = request;
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }
    
    
    Boolean  hasValidHeader(){
        //TODO
        return request.getHeader("X-API-KEY") != null
               && request.getHeader("X-API-KEY")
                       .equals("ValidApiKulcs") 
        //    && "application/json".equals(request.getHeader("Accepts"))
                ;
    }
    Boolean  isAuthorized(){
        return userRepo.findUserBySessionId(request.getHeader("token"))!=null; 
    } 

    Boolean  acceptsJson(){
        return request.getHeader("Accept").equals("application/json");
    }     
    
}
