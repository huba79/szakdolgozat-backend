package io.swagger.api;

import io.swagger.messages.BaseDataResponse;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.repositories.UsersRepository;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.LakeRepository;
import io.swagger.repositories.StageRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")

@RestController
public class BaseDataApiController implements BaseDataApi {

    private static final String ACCEPTEDAPIKEY ="ValidApiKulcs";     
    private final HttpServletRequest request;
    private static final Logger log = LoggerFactory.getLogger(BaseDataApiController.class);
    
    private final UsersRepository usersRepo;
    private final CompanyRepository companyRepo;
    private final LakeRepository lakesRepo;
    private final StageRepository stagesRepo;
    //TODO

    @org.springframework.beans.factory.annotation.Autowired
    public BaseDataApiController(HttpServletRequest request, UsersRepository usersRepo, CompanyRepository companyRepo, LakeRepository lakesRepo, StageRepository stagesRepo) {
        this.request = request;
        this.usersRepo = usersRepo;
        this.companyRepo = companyRepo;
        this.lakesRepo = lakesRepo;
        this.stagesRepo = stagesRepo;
    }
    //GETBASEDATA implementacio
    @Override
    public ResponseEntity<BaseDataResponse> getBaseData() {
        String apikey = request.getHeader("X-API-KEY");
        if( apikey !=null && apikey.equals(ACCEPTEDAPIKEY) ) {
            System.out.println("log");
                    //felepiteni egy BaseDataResponse-t

            return null;
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


    
}
