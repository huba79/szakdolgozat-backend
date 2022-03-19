package io.swagger.api;

import io.swagger.domain.Company;
import io.swagger.domain.Lake;
import io.swagger.domain.Price;
import io.swagger.domain.Stage;
import io.swagger.messages.BaseDataResponse;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.LakeRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import io.swagger.repositories.PriceRepository;
import io.swagger.repositories.StageRepository;


//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@RestController
public class BaseDataApiController implements BaseDataApi {
  
    private final HttpServletRequest request;
    //private static final Logger log = LoggerFactory.getLogger(BaseDataApiController.class);
    
    private final PriceRepository priceRepo;
    private final CompanyRepository companyRepo;
    private final LakeRepository lakeRepo;
    private final StageRepository stageRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public BaseDataApiController(HttpServletRequest request, PriceRepository priceRepo, CompanyRepository companyRepo, LakeRepository lakeRepo, StageRepository stageRepo) {
        this.request = request;
        this.priceRepo = priceRepo;
        this.companyRepo = companyRepo;
        this.lakeRepo = lakeRepo;
        this.stageRepo = stageRepo;
    }
    //GETBASEDATA implementacio
    @Override
    public ResponseEntity<BaseDataResponse> getBaseData(Long companyId) {
        String apikey = request.getHeader("X-API-KEY");
        if( apikey !=null && apikey.equals(AppConfig.APIKEY ) && companyId.equals(AppConfig.COMPANY) ) {
            System.out.println("delivered apiKey:\t"+apikey);

                    try {
                        Company company = companyRepo.findById(companyId).get();  
                        System.out.println("company data:"+company.toString());
                        
                        ArrayList<Lake> lakes = lakeRepo.findLakeByCompanyId(companyId);
                        System.out.println("Lakes data:"+lakes.toString());
                        
                        ArrayList<Stage> stages = new ArrayList<>();
                        ArrayList<Price> prices = new ArrayList<>();
                        
                     
                        for (Lake lake:lakes){
                            Long lakeId = lake.getId();
                            System.out.println("Lake id:\t"+ lakeId);
                            
                            stages.addAll(stageRepo.findStageByLakeId(Long.valueOf(lakeId)) );
                            System.out.println("Stages size:\t"+ stages.size()+"\n\n\n");
                            
                            prices.addAll( priceRepo.findPriceByLakeId(Long.valueOf(lakeId)) );
                            System.out.println("Prices size:\t"+ prices.size());
                        }                        
                      
                        return new ResponseEntity<BaseDataResponse>(new BaseDataResponse(company,
                                                                    lakes,prices,stages),HttpStatus.OK);

                    } catch (NoSuchElementException e) { 
                        System.out.println(e.getMessage());
                        return new ResponseEntity<BaseDataResponse>(HttpStatus.NO_CONTENT);
                    }

        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


    
}
