/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.domain.BlogEntry;
import io.swagger.domain.RuleOfConduct;
import io.swagger.messages.RulesResponse;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.RuleRepository;
import io.swagger.repositories.UserRepository;
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

public class RulesController implements RulesApi{
    @Autowired RuleRepository rulesRepo;
    @Autowired CompanyRepository companyRepo;    
    @Autowired HttpServletRequest request;
    @Autowired UserRepository usersRepo;

    @Override
    public ResponseEntity<ArrayList<RulesResponse>> getRulesByCompanyId(Long companyId) {
       
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.hasValidHeader() && validator.acceptsJson()) {
                 
            try {
                ArrayList<RuleOfConduct> rules= rulesRepo.findRuleByCompanyIdNative(companyId);
                if (rules.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {   
                    ArrayList<RulesResponse> rulesResponses = new ArrayList<>();
                    for(RuleOfConduct rule:rules){
                        RulesResponse ruleresponse = new RulesResponse(
                                rule.getCompanyId(),
                                rule.getLakeId(),
                                rule.getRuleText()
                        );
                        rulesResponses.add(ruleresponse);
                    }                     
                        return new ResponseEntity<>(rulesResponses,HttpStatus.OK);
                    } 
            }catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            }
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
    @Override
    public ResponseEntity<ArrayList<RulesResponse>> getRulesByLakeId(Long companyId, Long lakeId) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.hasValidHeader() && validator.acceptsJson()) {
                 
            try {
                ArrayList<RuleOfConduct> rules= rulesRepo.findRuleByCompanyAndLakeIdNative(companyId,lakeId);
                if (rules.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {   
                    ArrayList<RulesResponse> rulesResponses = new ArrayList<>();
                    for(RuleOfConduct rule:rules){
                        RulesResponse ruleresponse = new RulesResponse(
                                rule.getCompanyId(),
                                rule.getLakeId(),
                                rule.getRuleText()
                        );
                        rulesResponses.add(ruleresponse);
                    }                     
                        return new ResponseEntity<>(rulesResponses,HttpStatus.OK);
                    } 
            }catch(Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
            }
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}