package crengine.api;

import crengine.configuration.MyUUIDWrapper;
import crengine.domain.User;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import crengine.messages.LoginMessage;
import crengine.messages.LoginResponse;
import crengine.repositories.CompanyRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import org.springframework.web.bind.annotation.PathVariable;
import crengine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")

@RestController
public class AuthenticationController implements AuthenticationApi {
    @Autowired
    UserRepository usersRepo;
    @Autowired
    CompanyRepository companyRepo;
    @Autowired
    HttpServletRequest request;
     
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    //LOGIN implementacio
    @Override
    public ResponseEntity<LoginResponse> login(@Parameter(in = ParameterIn.DEFAULT, schema=@Schema()) @Valid @RequestBody LoginMessage body) {

        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid() && validator.acceptsJson()) {
            System.out.println("loginname:\t"+body.getLoginname()+"\npassword:\t"+body.getPassword()+"\n"+"client:\t"+request.getHeader("sender"));
            User foundUser= usersRepo.findUser(body.getLoginname(),body.getPassword(),body.getRole());
            if(foundUser == null){
                System.out.println("Not Found presented user");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else { 
                System.out.println("Found presented user");
                try {
                    //create a sessionID working also as token for now
                    String uuid = MyUUIDWrapper.getUUIDV5();
                    System.out.println("Generated sessionId is:"+uuid);
                    //setting additional data
                    foundUser.setLastLoginDate(new Date(System.currentTimeMillis()));
                    foundUser.setSessionID(uuid);
                    //and save it
                    usersRepo.save(foundUser);
                    //then return trhe updated user data
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
            }
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
    
//LOGOUT implementacio
    @Override
    public ResponseEntity<Void> logout(@Parameter(in = ParameterIn.PATH, required=true, schema=@Schema()) @PathVariable("id") Long id) {
        System.out.println("hello logout \n");
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        
        if(  validator.isApiKeyValid() && validator.isAuthorized()) {
            System.out.println("token of the request:\t"+request.getHeader("TOKEN"));
            
            Optional<User> foundUser = usersRepo.findById(id);
            String uuid =((User) foundUser.get() ).getSessionID();

            if(foundUser.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }  else if (!request.getHeader("token").equals(uuid)){
                    //if UUID does not match the token from the header
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                } else {                                    
                        User user = (User) foundUser.get();
                        user.setSessionID(null); //erasing sessionID after logout
                        usersRepo.save(user);
                        ResponseEntity response = new ResponseEntity<>( HttpStatus.OK);
                        return response;
                    }
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    

}
