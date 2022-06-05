/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.api;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import crengine.repositories.CompaniesRepository;
import crengine.repositories.UsersRepository;

//import org.springframework.context.annotation.ComponentScan;


/**
 *
 * @author huba
 */

public class RequestValidator {
    @Autowired Environment env;
    
    private final HttpServletRequest request;
    private final CompaniesRepository companyRepo;
    private final UsersRepository userRepo;
    
    public RequestValidator(  HttpServletRequest request, UsersRepository userRepo,
            CompaniesRepository companyRepo) {
        this.request = request;
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }    
    
    public Boolean  isApiKeyValid(){
        System.out.println("Validation of APIKEY..."+request.getHeader("X-API-KEY")+"\n");
        return request.getHeader("X-API-KEY") != null
               && request.getHeader("X-API-KEY")
                       .equals("ValidApiKulcs");
    }
   public Boolean  isAuthorized(){
            System.out.println("Validation of access permission..."+request.getHeader("token")+"\n");
            return userRepo.findUserBySessionId(request.getHeader("token"))!=null; 
    } 

    public Boolean  acceptsJson(){
            System.out.println("Validation of input structure..."+request.getHeader("Accept")+"\n");
            return request.getHeader("Accept").equals("application/json");
    }   
}
