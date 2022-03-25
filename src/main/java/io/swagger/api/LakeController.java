/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.domain.Lake;
import io.swagger.messages.LakeResponse;
import io.swagger.messages.LakesResponse;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.LakeRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class LakeController implements LakeService{
    @Autowired
    LakeRepository lakeRepo;
    
    @Autowired
    CompanyRepository companyRepo;    
    
    @Autowired
    HttpServletRequest request;    

    @Override
    public ResponseEntity<LakesResponse> getLakes(Long companyId) {
            
        RequestValidator validator = new RequestValidator(request); 
        if(  validator.hasValidHeader() && validator.acceptsJson()) {

                ArrayList<Lake> lakes = lakeRepo.findLakeByCompanyId(companyId);
                ArrayList<LakeResponse> lakeResponse = new ArrayList();
                
                if (lakes.size()==0) return new ResponseEntity<LakesResponse>(HttpStatus.NO_CONTENT); 
                
                for(Lake lake: lakes){
                    lakeResponse.add(
                        new LakeResponse(
                            lake.getId(),
                            lake.getLakeName(),
                            lake.getCompanyId(),
                            lake.getLakeAddress(),
                            lake.getReservationsSystem(),
                            lake.getLakeSize(),
                            lake.getStages(),
                            lake.getServices()
                        )
                    );
                }                
                return new ResponseEntity<LakesResponse>(new LakesResponse(lakeResponse),HttpStatus.OK );    
        } else return new ResponseEntity<LakesResponse>(HttpStatus.UNAUTHORIZED);    
    }

    @Override
    public ResponseEntity<LakeResponse> getLakeById(Long companyId, Long lakeId) {
        //apikey validaciot egysegesiteni kellene, alabb egy masik, talan jobb megkozelites         
        if( request.getHeader("X-API-KEY") !=null 
                 && request.getHeader("X-API-KEY").equals(companyRepo.findById(companyId).get().getApiKey()) && 
                 "application/json".equals(request.getHeader("Accepts"))) {

                    try {
                        Lake foundLake = (lakeRepo.findById(lakeId)).get();
                        LakeResponse lakeResponse = new LakeResponse(
                            foundLake.getId(),
                            foundLake.getLakeName(),
                            foundLake.getCompanyId(),
                            foundLake.getLakeAddress(),
                            foundLake.getReservationsSystem(),
                            foundLake.getLakeSize(),
                            foundLake.getStages(),
                            foundLake.getServices()                            
                        );                           
                           return new ResponseEntity<>(lakeResponse, HttpStatus.OK);
                    } catch(NoSuchElementException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                                   
                } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);    
    }

    
}
