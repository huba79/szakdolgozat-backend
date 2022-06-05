package crengine.api;

import crengine.domain.Reservation;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import crengine.messages.ReservationMessage;
import crengine.messages.ReservationResponse;
import crengine.repositories.CompanyRepository;
import crengine.repositories.PaymentRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import crengine.repositories.ReservationRepository;
import crengine.repositories.OrderedItemRepository;
import crengine.repositories.UserRepository;
import crengine.services.ReservationService;
import crengine.services.StageUnavailableException;



@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")
@RestController
public class ReservationController implements ReservationApi {
    
    @Autowired ReservationService service;
    private static final  Logger log = LoggerFactory.getLogger(ReservationController.class);
    @Autowired
    private  HttpServletRequest request;
    @Autowired ReservationRepository reservationRepo;
    @Autowired PaymentRepository paymentRepo;
    @Autowired OrderedItemRepository orderedServicesRepo;    
    @Autowired CompanyRepository companyRepo;
    @Autowired UserRepository usersRepo;    
    
    @Override
    public ResponseEntity<ReservationResponse> getReservationById(Long id) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid()&&validator.acceptsJson() ){
            if( validator.isAuthorized()){
                Reservation reservation = reservationRepo.findByIdNative(id);
                if (reservation ==null) { 
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else { return new ResponseEntity<>(service.getOneById(id),HttpStatus.OK);
                }
            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ArrayList<ReservationResponse>> getReservationsByQuery(
            @Nullable Long lakeId, @Nullable Long stageId,@Nullable Long userId, 
            @Nullable Date dateFrom, @Nullable Date dateTo, @Nullable String status) {
        
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid()&&validator.acceptsJson() ){
            if( validator.isAuthorized()){
                return new ResponseEntity (service.getAllByQuery(lakeId, stageId, userId, dateFrom, dateTo, status),HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ReservationResponse> newReservation(ReservationMessage body) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid()&&validator.acceptsJson() ){
            if( validator.isAuthorized()){
                return new ResponseEntity<>(service.addOne(body),HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ReservationResponse> updateReservation(Long id,ReservationMessage body) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.acceptsJson() ){
            if(validator.isApiKeyValid()&& validator.isAuthorized()){
            
                try {
                    return new ResponseEntity<>(service.updateOne(id, body), HttpStatus.OK);
                } catch (StageUnavailableException stageUnavailableException) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                } catch (IllegalArgumentException illegalArgumentException) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
     

            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
