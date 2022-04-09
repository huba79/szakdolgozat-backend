package io.swagger.api;

import io.swagger.domain.OrderedItem;
import io.swagger.domain.Payment;
import io.swagger.domain.Reservation;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.messages.ReservationMessage;
import io.swagger.messages.ReservationResponse;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.PaymentRepository;
import java.util.Date;
import io.swagger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import io.swagger.repositories.ReservationRepository;
import io.swagger.repositories.OrderedItemRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
//@RestController
public class ReservationControllerBackup implements ReservationApi {

    private static final  Logger log = LoggerFactory.getLogger(ReservationControllerBackup.class);
    @Autowired
    private  HttpServletRequest request;
    @Autowired CompanyRepository companyRepo;
    @Autowired UserRepository usersRepo;
    @Autowired ReservationRepository reservationRepo;
    @Autowired PaymentRepository paymentRepo;
    @Autowired OrderedItemRepository orderedServicesRepo;    
    
    @Override
    public ResponseEntity<ReservationResponse> getReservationById(Long id) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.acceptsJson() ){
            if(validator.hasValidHeader()&& validator.isAuthorized()){
                Reservation reservation = reservationRepo.findByIdNative(id);
                if (reservation ==null) { 
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    System.out.println("Reservation ok: \t"+reservation.toString());
                    ArrayList<OrderedItem> orderedServices = orderedServicesRepo.findOrderedServiceByReservationIdNative(reservation.getId());
                    Payment payment = paymentRepo.findPaymentByReservationIdNative(reservation.getId());
                    ReservationResponse response = new ReservationResponse(
                            reservation.getId(),
                            reservation.getLakeId(),
                            reservation.getUserId(),
                            reservation.getStageId(),
                            reservation.getDateFrom(),
                            reservation.getDateTo(),
                            reservation.getReservationStatus() ,
                            orderedServices,
                            payment,
                        (usersRepo.findById(reservation.getUserId()) ).get().getDisplayName()
                    );           
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ArrayList<ReservationResponse>> getReservationsByQuery(
            @Nullable Long lakeId, @Nullable Long stageId,@Nullable Long userId, 
            @Nullable Date dateFrom, @Nullable Date dateTo, @Nullable String status) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.acceptsJson() ){
            if(validator.hasValidHeader()&& validator.isAuthorized()){
                ArrayList<Reservation> reservations = new  ArrayList<>();
                reservations = 
                        reservationRepo.getReservationsByQuery(lakeId, stageId, userId, dateFrom, dateTo, status);

                ArrayList<ReservationResponse> responseList = new ArrayList<>();
                Payment payment=null;
                ArrayList<OrderedItem> orderedServices= new ArrayList<>();
                
                if (!reservations.isEmpty()) { 
                    
                    System.out.println("request OK, got some reservations");
                    for(Reservation reservation:reservations){
                        try {
                            orderedServices = orderedServicesRepo.findOrderedServiceByReservationIdNative(reservation.getId());
                            payment = paymentRepo.findPaymentByReservationIdNative(reservation.getId());
                            ReservationResponse response = new ReservationResponse(
                                reservation.getId(),
                                reservation.getLakeId(),
                                reservation.getUserId(),
                                reservation.getStageId(),
                                reservation.getDateFrom(),
                                reservation.getDateTo(),
                                reservation.getReservationStatus() ,
                                orderedServices,
                                payment,
                                (usersRepo.findById(reservation.getUserId()) ).get().getDisplayName()
                            ); 
                            responseList.add(response);
                        }
                        catch(Exception e) {
                            System.out.println(e.getLocalizedMessage());
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }   
                    return new ResponseEntity<>(responseList,HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ReservationResponse> newReservation(ReservationMessage body) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.acceptsJson() ){
            if(validator.hasValidHeader()&& validator.isAuthorized()){
                    //TODO magamnak: nincs mese meg kellene oldani a tranzakcionalis mentest
                    Payment postedPayment = null;
                    ArrayList<OrderedItem> postedOrderedItems =new ArrayList<>();
                    Reservation postedReservation =null;
                    try{
                        postedPayment = body.getPayment();
                        postedOrderedItems =(ArrayList) body.getOrderedItems();

                        System.out.println("Payment:\t"+postedPayment.toString()+" \n Ordered Services:\t"+postedOrderedItems.toString());
                        
                        postedReservation = new Reservation(
                                body.getReservationId(),
                                body.getLakeId(),
                                body.getStageId(),
                                body.getUserId(),
                                body.getDateFrom(),
                                body.getDateTo(),
                                body.getStatus()
                        );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }               
                    Reservation savedReservation = null;
                    Payment savedPayment = null;                   
                        ArrayList<OrderedItem> savedOrderedItems = new ArrayList<>();                    
                    
                    try {
                        savedReservation = reservationRepo.save(postedReservation);
                        if (postedPayment.getReservationId() !=null){
                            //no payment is OK at first pot so far.
                            postedPayment.setReservationId(postedReservation.getId());
                            savedPayment = paymentRepo.save(postedPayment);
                        } else System.out.println("Empty payment record!\n");
                        
                        if(!postedOrderedItems.isEmpty()){
                            //no ordered item mean no valid reservation, will not be saved
                            for(OrderedItem items:postedOrderedItems){
                                items.setReservationId(postedReservation.getId());
                            } 
                            savedOrderedItems = (ArrayList) orderedServicesRepo.saveAll(postedOrderedItems);
                        } else { 
                                System.out.println("Empty cart! \n");
                                reservationRepo.deleteById(body.getReservationId());
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                        
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);                        
                    }   
                    ReservationResponse reservationResponse = new ReservationResponse(
                        savedReservation.getId(),
                        savedReservation.getLakeId(),
                        savedReservation.getStageId(),
                        savedReservation.getUserId(),
                        savedReservation.getDateFrom(),
                        savedReservation.getDateTo(),
                        savedReservation.getReservationStatus() ,
                        savedOrderedItems,
                        savedPayment,
                        (usersRepo.findById(savedReservation.getUserId()) ).get().getDisplayName()
                    );
                    return new ResponseEntity<>(reservationResponse,HttpStatus.OK);

            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ReservationResponse> updateReservation(Long id,ReservationMessage body) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.acceptsJson() ){
            if(validator.hasValidHeader()&& validator.isAuthorized()){

                    Payment postedPayment = null;
                    ArrayList<OrderedItem> postedOrderedItems =new ArrayList<>();
                    Reservation postedReservation =null;
                    try{
                        postedPayment = body.getPayment();
                        postedOrderedItems =(ArrayList) body.getOrderedItems();
                        postedReservation = new Reservation(
                                body.getReservationId(),
                                body.getLakeId(),
                                body.getStageId(),
                                body.getUserId(),
                                body.getDateFrom(),
                                body.getDateTo(),
                                body.getStatus()
                        );
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }               
                    Reservation savedReservation = null;
                    Payment savedPayment = null;                   
                        ArrayList<OrderedItem> savedOrderedItems = new ArrayList<>();                    
                    try {
                        postedPayment.setReservationId(body.getReservationId());
                        savedReservation = reservationRepo.save(postedReservation);
                        
                        if (postedPayment.getReservationId() !=null){
                            //TODO: no payment is not always OK on update, futher checks needed
                            postedReservation.setId(body.getReservationId());
                            savedPayment = paymentRepo.save(postedPayment);
                        } else System.out.println("Empty payment record!\n");
                        
                        if(!postedOrderedItems.isEmpty()){
                            //no ordered item mean no valid reservation, will not be saved
                            for(OrderedItem items:postedOrderedItems){
                                items.setReservationId(postedReservation.getId());
                            } 
                            savedOrderedItems = (ArrayList) orderedServicesRepo.saveAll(postedOrderedItems);
                        } else { 
                                System.out.println("Empty cart! \n");
                                reservationRepo.deleteById(body.getReservationId());
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);                        
                    }   
                    ReservationResponse reservationResponse = new ReservationResponse(
                        savedReservation.getId(),
                        savedReservation.getLakeId(),
                        savedReservation.getStageId(),
                        savedReservation.getUserId(),
                        savedReservation.getDateFrom(),
                        savedReservation.getDateTo(),
                        savedReservation.getReservationStatus() ,
                        savedOrderedItems,
                        savedPayment,
                        (usersRepo.findById(savedReservation.getUserId()) ).get().getDisplayName()
                    );
                    return new ResponseEntity<>(reservationResponse,HttpStatus.OK);

            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    }
