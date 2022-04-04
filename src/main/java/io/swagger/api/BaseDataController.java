package io.swagger.api;

import io.swagger.domain.Company;
import io.swagger.domain.Lake;
import io.swagger.domain.Service;
import io.swagger.domain.Stage;
import io.swagger.messages.BaseDataResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.LakeRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import io.swagger.repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repositories.ServiceRepository;
import io.swagger.repositories.UserRepository;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@RestController
public class BaseDataController implements BaseDataApi {
    @Autowired HttpServletRequest request;
    @Autowired ServiceRepository priceRepo;
    @Autowired CompanyRepository companyRepo;
    @Autowired LakeRepository lakeRepo;
    @Autowired StageRepository stageRepo;
    @Autowired UserRepository usersRepo;

    //private static final Logger log = LoggerFactory.getLogger(BaseDataController.class);

    @Override
    public ResponseEntity<BaseDataResponse> getBaseData(Long companyId) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.hasValidHeader()) {

                    try {
                        Company company = companyRepo.findById(companyId).get();  
                        System.out.println("company data:"+company.toString());
                        
                        ArrayList<Lake> lakes = lakeRepo.findLakeByCompanyId(companyId);
                        System.out.println("Lakes data:"+lakes.toString());
                        
                        ArrayList<Stage> stages = new ArrayList<>();
                        ArrayList<Service> prices = new ArrayList<>();
                        
                     
                        for (Lake lake:lakes){
                            Long lakeId = lake.getId();
                            System.out.println("Lake id:\t"+ lakeId);
                            
                            stages.addAll(stageRepo.findStageByLakeIdNative(lakeId) );
                            System.out.println("Stages size:\t"+ stages.size()+"\n\n\n");
                            
                            prices.addAll(priceRepo.findServiceByLakeIdNative(lakeId));
                            //TODO meg kell nezni mi a banator golyalaszarert nem megy, mint pl a stageRepo
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
