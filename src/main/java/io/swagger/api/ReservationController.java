package io.swagger.api;

import io.swagger.domain.OrderedService;
import io.swagger.domain.Payment;
import io.swagger.domain.Reservation;
import io.swagger.domain.ReservationStatusEnum;
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
import io.swagger.repositories.OrderedServiceRepository;
import io.swagger.repositories.PaymentRepository;
import io.swagger.repositories.ReservationsRepository;
import java.util.Date;
import io.swagger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.Nullable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@RestController
public class ReservationController implements ReservationService {

    private static final  Logger log = LoggerFactory.getLogger(ReservationController.class);
    @Autowired
    private  HttpServletRequest request;
    @Autowired CompanyRepository companyRepo;
    @Autowired UserRepository usersRepo;
    @Autowired ReservationsRepository reservationRepo;
    @Autowired PaymentRepository paymentRepo;
    @Autowired OrderedServiceRepository orderedServicesRepo;    
    
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
                    ArrayList<OrderedService> orderedServices = orderedServicesRepo.findOrderedServiceByReservationIdNative(reservation.getId());
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
                            payment
                    );           
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ArrayList<ReservationResponse>> getReservationsByQuery(@Nullable Long lakeId, @Nullable Long stageId,
            @Nullable Long userId, @Nullable Date dateFrom, @Nullable Date dateTo, @Nullable ReservationStatusEnum status) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.acceptsJson() ){
            if(validator.hasValidHeader()&& validator.isAuthorized()){
                ArrayList<Reservation> reservations = new  ArrayList<>();
                reservations = (ArrayList) reservationRepo
                        .reservationsbycriteriaProcedureName(userId, lakeId, 
                                stageId, dateFrom, dateTo, status);
                
                ArrayList<ReservationResponse> responseList = new ArrayList<>();
                Payment payment;
                ArrayList<OrderedService> orderedServices= new ArrayList<>();
                
                if (reservations !=null) { 
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
                                    payment
                            ); 
                            responseList.add(response);
                        }
                        catch(Exception e) {
                            System.out.println(e.getLocalizedMessage());
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        return new ResponseEntity<>(responseList,HttpStatus.OK);
                    }   return new ResponseEntity<>(null,HttpStatus.OK);
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
                    ArrayList<OrderedService> postedOrderedServices =new ArrayList<>();
                    Reservation postedReservation =null;
                    try{
                        postedPayment = body.getPayment();
                        postedOrderedServices =(ArrayList) body.getOrderedItems();
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
                        ArrayList<OrderedService> savedOrderedServices = new ArrayList<>();                    
                    try {
                        savedReservation = reservationRepo.save(postedReservation);
                        savedPayment = paymentRepo.save(postedPayment);
                        savedOrderedServices = (ArrayList) orderedServicesRepo.saveAll(postedOrderedServices);
                    }catch (Exception e){
                        System.out.println(e.getLocalizedMessage());
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
                            savedOrderedServices,
                            savedPayment
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
                    //TODO magamnak: nincs mese meg kellene oldani a tranzakcionalis mentest
                    Payment postedPayment = null;
                    ArrayList<OrderedService> postedOrderedServices =new ArrayList<>();
                    Reservation postedReservation =null;
                    try{
                        postedPayment = body.getPayment();
                        postedOrderedServices =(ArrayList) body.getOrderedItems();
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
                        ArrayList<OrderedService> savedOrderedServices = new ArrayList<>();                    
                    try {
                        savedReservation = reservationRepo.save(postedReservation);
                        savedPayment = paymentRepo.save(postedPayment);
                        savedOrderedServices = (ArrayList) orderedServicesRepo.saveAll(postedOrderedServices);
                    }catch (Exception e){
                        System.out.println(e.getLocalizedMessage());
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
                            savedOrderedServices,
                            savedPayment
                    );
                    return new ResponseEntity<>(reservationResponse,HttpStatus.OK);

            } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    }
