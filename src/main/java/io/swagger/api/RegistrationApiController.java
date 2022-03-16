package io.swagger.api;

import io.swagger.configuration.MyUUIDWrapper;
import io.swagger.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.repositories.UsersRepository;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import io.swagger.messages.RegistrationMessage;
import io.swagger.messages.RegistrationResponse;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@RestController
public class RegistrationApiController implements RegistrationApi {

    private static final String ACCEPTEDAPIKEY ="ValidApiKulcs";  //baaaaaaaaasic solution

    private final UsersRepository usersrepo;

    private static final Logger log = LoggerFactory.getLogger(RegistrationApiController.class);

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RegistrationApiController(HttpServletRequest request,UsersRepository pUsersRepo) {
        this.request = request;
        this.usersrepo = pUsersRepo;
    }
    //REGISTRATION implementacio

    @Override
    public ResponseEntity<RegistrationResponse> registration(RegistrationMessage body) {
         if( request.getHeader("X-API-KEY") !=null 
                 && request.getHeader("X-API-KEY").equals(ACCEPTEDAPIKEY) && 
                 "application/json".equals(request.getHeader("Accepts"))) {

                    try {
                        String sessionID = MyUUIDWrapper.getUUIDV5();
                        
                        
                        User newUser = new User();
                            newUser.setDisplayName( body.getDisplayName() );
                            newUser.setNickName( body.getNickName() );
                            newUser.setEmailAddress( body.getEmailAddress() );
                            newUser.setPassword( body.getPassword() );
                            newUser.setCompanyId( body.getCompanyId() );
                            newUser.setGroupName("USER");
                            newUser.setCreatedDate( new Date(System.currentTimeMillis()) );
                            newUser.setLastLoginDate( new Date(System.currentTimeMillis()) );

                        usersrepo.save(newUser);
                        ArrayList<User> foundUsers = usersrepo.findUserByEmailAddress(body.getEmailAddress());
                        
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
                        

                    } catch (NoSuchAlgorithmException ex) {
                        java.util.logging.Logger.getLogger(RegistrationApiController.class.getName()).log(Level.SEVERE, null, ex);
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    } catch (UnsupportedEncodingException ex) {
                        java.util.logging.Logger.getLogger(RegistrationApiController.class.getName()).log(Level.SEVERE, null, ex);
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
   
    };
}
