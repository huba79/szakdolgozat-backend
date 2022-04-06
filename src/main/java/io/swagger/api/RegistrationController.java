package io.swagger.api;

import io.swagger.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import io.swagger.messages.RegistrationMessage;
import io.swagger.messages.RegistrationResponse;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.UserRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@RestController
public class RegistrationController implements RegistrationApi {
    
    @Autowired CompanyRepository companyRepo;
    @Autowired UserRepository usersRepo;
    @Autowired HttpServletRequest request;
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    //REGISTRATION implementacio

    @Override
    public ResponseEntity<RegistrationResponse> registration(RegistrationMessage body) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo);  
        if(  validator.hasValidHeader() && validator.acceptsJson() ) {

                    try {

                        User newUser = new User();
                            newUser.setDisplayName( body.getDisplayName() );
                            newUser.setNickName( body.getNickName() );
                            newUser.setEmailAddress( body.getEmailAddress() );
                            newUser.setPassword( body.getPassword() );
                            newUser.setCompanyId( body.getCompanyId() );
                            newUser.setGroupName("USER");
                            newUser.setCreatedDate( new Date(System.currentTimeMillis()) );
                            newUser.setLastLoginDate( new Date(System.currentTimeMillis()) );

                        usersRepo.save(newUser);
                        ArrayList<User> foundUsers = usersRepo.findUserByEmailAddress(body.getEmailAddress());
                        
                        if(foundUsers.size() ==1 && foundUsers.get(0) !=null){
                           User found = foundUsers.get(0);
                            RegistrationResponse response = new RegistrationResponse();
                           response.setId(found.getId());
                           response.setDisplayName(found.getDisplayName());
                           response.setNickName(found.getNickName());
                           response.setCompanyId(found.getCompanyId());
                           response.setSessionID(found.getSessionID());
                           
                           return new ResponseEntity<>(response, HttpStatus.OK);
                                   
                        } else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        

                    } catch (NoSuchElementException ex) {
                        java.util.logging.Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
   
    };
}
