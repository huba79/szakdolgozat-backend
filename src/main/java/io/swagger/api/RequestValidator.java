/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;


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
        System.out.println("Validation of APIKEY...");
        return request.getHeader("X-API-KEY") != null
               && request.getHeader("X-API-KEY")
                       .equals("ValidApiKulcs");
    }
    Boolean  isAuthorized(){
            System.out.println("Validation of access permission...");
            return userRepo.findUserBySessionId(request.getHeader("token"))!=null; 
    } 

    Boolean  acceptsJson(){
            System.out.println("Validation of input structure...");
            return request.getHeader("Accept").equals("application/json");
    }     
    
}
