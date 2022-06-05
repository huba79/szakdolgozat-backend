/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.api;

import crengine.domain.RuleOfConduct;
import crengine.messages.RulesResponse;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import crengine.repositories.CompaniesRepository;
import crengine.repositories.RulesRepository;
import crengine.repositories.UsersRepository;

/**
 *
 * @author huba
 */
@RestController
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")

public class RulesController implements RulesApi{
    @Autowired RulesRepository rulesRepo;
    @Autowired CompaniesRepository companyRepo;    
    @Autowired HttpServletRequest request;
    @Autowired UsersRepository usersRepo;

    @Override
    public ResponseEntity<ArrayList<RulesResponse>> getRulesByCompanyId(Long companyId) {
       
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid() && validator.acceptsJson()) {
                 
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
        if(  validator.isApiKeyValid() && validator.acceptsJson()) {
                 
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
