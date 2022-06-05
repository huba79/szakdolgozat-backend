/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.api;

import crengine.domain.Lake;
import crengine.messages.LakeResponse;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import crengine.repositories.CompaniesRepository;
import crengine.repositories.LakesRepository;
import crengine.repositories.UsersRepository;

/**
 *
 * @author huba
 */
@RestController
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")

public class LakeController implements LakeApi{
    @Autowired LakesRepository lakeRepo;
    @Autowired CompaniesRepository companyRepo; 
    @Autowired HttpServletRequest request;
    @Autowired UsersRepository usersRepo;    

    @Override
    public ResponseEntity<ArrayList<LakeResponse>> getLakes(Long companyId) {
            
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid() && validator.acceptsJson()) {

                ArrayList<Lake> lakes = lakeRepo.findLakeByCompanyId(companyId);
                ArrayList<LakeResponse> lakeResponses = new ArrayList();
                
                if (lakes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
                
                for(Lake lake: lakes){
                    lakeResponses.add(
                        new LakeResponse(
                            lake.getId(),
                            lake.getLakeName(),
                            lake.getCompanyId(),
                            lake.getLakeAddress(),
                            lake.getReservationsSystem(),
                            lake.getLakeSize()
                        )
                    );
                }                
                return new ResponseEntity<>(lakeResponses,HttpStatus.OK );    
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);    
    }

    @Override
    public ResponseEntity<LakeResponse> getLakeById(Long companyId, Long lakeId) {
        //apikey validaciot egysegesiteni kellene, alabb egy masik, talan jobb megkozelites         
        if( request.getHeader("X-API-KEY") !=null 
                // && request.getHeader("X-API-KEY").equals(companyRepo.findById(companyId).get().getApiKey())  
                && request.getHeader("Accept").equals("application/json") ) {

                    try {
                        Lake foundLake = (lakeRepo.findById(lakeId)).get();
                        LakeResponse lakeResponse = new LakeResponse(
                            foundLake.getId(),
                            foundLake.getLakeName(),
                            foundLake.getCompanyId(),
                            foundLake.getLakeAddress(),
                            foundLake.getReservationsSystem(),
                            foundLake.getLakeSize()                            
                        );                           
                           return new ResponseEntity<>(lakeResponse, HttpStatus.OK);
                    } catch(NoSuchElementException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                                   
                } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);    
    }

    
}
