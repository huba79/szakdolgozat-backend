package crengine.api;

import crengine.domain.Company;
import crengine.domain.Lake;
import crengine.domain.RuleOfConduct;
import crengine.domain.Service;
import crengine.domain.Stage;
import crengine.messages.BaseDataResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import crengine.repositories.CompanyRepository;
import crengine.repositories.LakeRepository;
import crengine.repositories.RuleRepository;
import crengine.repositories.ServiceRepository;
import crengine.repositories.ServiceRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import crengine.repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import crengine.repositories.UserRepository;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@RestController
public class BaseDataController implements BaseDataApi {
    @Autowired HttpServletRequest request;
    @Autowired ServiceRepository serviceRepo;
    @Autowired CompanyRepository companyRepo;
    @Autowired LakeRepository lakeRepo;
    @Autowired StageRepository stageRepo;
    @Autowired UserRepository usersRepo;
    @Autowired RuleRepository rulesRepo;

    //private static final Logger log = LoggerFactory.getLogger(BaseDataController.class);

    @Override
    public ResponseEntity<BaseDataResponse> getBaseData(Long companyId) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid()) {

                    try {
                        Company company = companyRepo.findById(companyId).get();  
                        System.out.println("company data:"+company.toString());
                        
                        ArrayList<Lake> lakes = lakeRepo.findLakeByCompanyId(companyId);
                        System.out.println("Lakes data:"+lakes.toString());
                        
                        ArrayList<Stage> stages = new ArrayList<>();
                        ArrayList<Service> services = new ArrayList<>();
                        ArrayList<RuleOfConduct> rules = new ArrayList<>();
                        
                     
                        for (Lake lake:lakes){
                            Long lakeId = lake.getId();
                            System.out.println("Lake id:\t"+ lakeId);
                            
                            stages.addAll(stageRepo.findStageByLakeIdNative(lakeId) );
                            System.out.println("Stages size:\t"+ stages.size()+"\n\n\n");
                            
                            services.addAll(serviceRepo.findServiceByLakeIdNative(lakeId));
                            System.out.println("Prices size:\t"+ services.size());
                            
                            rules.addAll(rulesRepo.findRuleByLakeIdNative(lakeId));
                            System.out.println("Prices size:\t"+ rules.size());
                        }                        
                      
                        return new ResponseEntity<BaseDataResponse>(new BaseDataResponse(company,
                                                                    lakes,services,stages,rules),HttpStatus.OK);

                    } catch (NoSuchElementException e) { 
                        System.out.println(e.getMessage());
                        return new ResponseEntity<BaseDataResponse>(HttpStatus.NO_CONTENT);
                    }

        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


    
}