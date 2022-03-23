package io.swagger.api;

import io.swagger.configuration.MyUUIDWrapper;
import io.swagger.domain.User;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.messages.LoginMessage;
import io.swagger.messages.LoginResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")

@RestController
public class AuthenticationController implements AuthenticationService {
    @Autowired
    private final UserRepository usersrepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthenticationController(HttpServletRequest request,UserRepository pUsersRepo) {
        this.request = request;
        this.usersrepo = pUsersRepo;
    }
    //LOGIN implementacio
    @Override
    public ResponseEntity<LoginResponse> login(@Parameter(in = ParameterIn.DEFAULT, schema=@Schema()) @Valid @RequestBody LoginMessage body) {

        RequestValidator validator = new RequestValidator(request); 
        if(  validator.hasValidHeader() && validator.acceptsJson()) {
            System.out.println("loginname:\t"+body.getLoginname()+"\npassword:\t"+body.getPassword()+"\n"+"client:\t"+request.getHeader("sender"));
            ArrayList<User> foundusers= usersrepo.findUserByEmailAddress(body.getLoginname());
            if(foundusers.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else if(foundusers.size()>1){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else { //found exactly 1 user
                System.out.println("Found only 1 user"+"\n");
                User foundUser = foundusers.get(0);
                if ( body.getPassword().equals(foundUser.getPassword()) ) {

                        try {
                            String uuid = MyUUIDWrapper.getUUIDV5();
                            System.out.println("His UUID is:"+uuid+"\n");
                            foundUser.setLastLoginDate(new Date(System.currentTimeMillis()));
                            foundUser.setSessionID(uuid);
                            usersrepo.save(foundUser);
                            
                            LoginResponse response = new  LoginResponse(foundUser.getId(),
                                        foundUser.getDisplayName(),foundUser.getNickName(),
                                        foundUser.getGroupName(),foundUser.getSessionID() );
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        
                        } catch (NoSuchAlgorithmException ex) {
                            java.util.logging.Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        } catch (UnsupportedEncodingException ex) {
                            java.util.logging.Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }


                } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
    
//LOGOUT implementacio
    @Override
    public ResponseEntity<Void> logout(@Parameter(in = ParameterIn.PATH, required=true, schema=@Schema()) @PathVariable("id") Long id) {
        System.out.println("hello logout \n");
        RequestValidator validator = new RequestValidator(request); 
        if(  validator.hasValidHeader() && validator.isAuthorized()) {
            System.out.println("token of the request:\t"+request.getHeader("TOKEN"));
            Optional<User> foundUser = usersrepo.findById(id);
            String uuid =((User) foundUser.get() ).getSessionID();

                System.out.println("token of the user:\t"+uuid);
            if(foundUser.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }  else if (!request.getHeader("token").equals(uuid)){
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                } else {                                    
                        User user = (User) foundUser.get();
                        user.setSessionID(null); //erasing sessionID after logout
                        usersrepo.save(user);
                        ResponseEntity response = new ResponseEntity<>( HttpStatus.OK);
                        return response;
                    }
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
