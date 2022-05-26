/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.services;

import io.swagger.domain.OrderedItem;
import io.swagger.domain.Payment;
import io.swagger.domain.Reservation;
import io.swagger.messages.ReservationMessage;
import io.swagger.messages.ReservationResponse;
import io.swagger.repositories.OrderedItemRepository;
import io.swagger.repositories.PaymentRepository;
import io.swagger.repositories.ReservationRepository;
import io.swagger.repositories.ServiceRepository;
import io.swagger.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 *
 * @author huba
 */
    @Service
    public class ReservationService {
 

    @Autowired ReservationRepository reservationRepo;
    @Autowired OrderedItemRepository orderRepo; 
    @Autowired PaymentRepository paymentRepo;
    @Autowired ServiceRepository serviceRepo; 
    @Autowired UserRepository userRepo;    

    public ReservationResponse addOne(ReservationMessage rMessage) 
            throws StageUnavailableException , IllegalArgumentException, EmptyCartException {
        Reservation postedReservation ;
        ArrayList<OrderedItem> postedOrders;
        Payment postedPayment;
        
        //try to unpack received data
        try {
            postedReservation = new Reservation(
            rMessage.getLakeId(),
            rMessage.getStageId(),
            rMessage.getUserId(),
            rMessage.getDateFrom(),
            rMessage.getDateTo(),
            rMessage.getStatus()
            );
            postedOrders = (ArrayList)rMessage.getOrderedItems();
            postedPayment  = rMessage.getPayment();
        }  catch (Exception e){
            //or throw exception
           throw  new IllegalArgumentException();        
        }  
        //if the stage is available for reservation     

        System.out.println("Reservation Validator result:\t"+reservationRepo.isStageTaken( postedReservation.getStageId(), 


            postedReservation.getDateFrom(), postedReservation.getDateTo() ,null)+"StageId:\t"+postedReservation.getStageId()+"\tDateFrom:"+postedReservation.getDateFrom()+
              "\tDateTo:"+postedReservation.getDateTo() );
        
        
        
       if( reservationRepo.isStageTaken( postedReservation.getStageId(), 
               postedReservation.getDateFrom(), postedReservation.getDateTo() ,null) == 0L ) {            

            
           Reservation savedReservation = new Reservation();
           Payment savedPayment =  new Payment();           
           ArrayList<OrderedItem> savedOrders = new ArrayList<>();  

            //if there are some ordered items, we can save the reservation
       // if( !postedOrders.isEmpty()){

            try{
                savedReservation = reservationRepo.save(postedReservation);
                if( postedOrders !=null && !postedOrders.isEmpty()){
                    for (OrderedItem ordered:postedOrders){
                        ordered.setReservationId(savedReservation.getId());
                        ordered.setPrice(serviceRepo.findPriceByServiceIdNative( ordered.getServiceId()) * ordered.getAmountOrdered());
                    }
                    savedOrders = (ArrayList) orderRepo.saveAll(postedOrders);
                } else {
                    savedOrders=null;
                }

                if( postedPayment!=null){
                    //if there is some payment info then let's try to save it too
                    postedPayment.setReservationId(savedReservation.getId());
                    savedPayment = paymentRepo.save(postedPayment);
                } else {
                    savedPayment = null;
                }

                //when everything saved , let's return a proper response                    

                return new ReservationResponse(
                        savedReservation.getId(),
                        savedReservation.getLakeId(),
                        savedReservation.getStageId(),
                        savedReservation.getUserId(),
                        savedReservation.getDateFrom(),
                        savedReservation.getDateTo(),
                        savedReservation.getReservationStatus(),
                        savedOrders,
                        savedPayment,
                        (userRepo.findById(savedReservation.getUserId()) ).get().getDisplayName()
                    ); 

            } catch (IllegalArgumentException  e) {
                throw new IllegalArgumentException("Corrupted or incomplete message! Request rejected.");
            }   

        //} else throw new EmptyCartException("Empty order list! Request rejected.");   
        
       } else { 
           throw new StageUnavailableException("The stage is unavailable in the requested period! Request rejected.");
       }
    }
    
    public ReservationResponse updateOne(Long reservationId, ReservationMessage rMessage) 
            throws StageUnavailableException, IllegalArgumentException {
        
        Reservation postedReservation ;
//        ArrayList<OrderedItem> postedOrders;
//        Payment postedPayment;
        
        //try to unpack received data
        try {
            postedReservation = new Reservation(
            reservationId,
            rMessage.getLakeId(),
            rMessage.getStageId(),
            rMessage.getUserId(),
            rMessage.getDateFrom(),
            rMessage.getDateTo(),
            rMessage.getStatus()
            );

//            postedOrders = (ArrayList)rMessage.getOrderedItems();
//            postedPayment  = rMessage.getPayment();
            System.out.println("Checking reservations put message body....OK");

//            postedOrders = (ArrayList)rMessage.getOrderedItems();
//            postedPayment  = rMessage.getPayment();
            System.out.println("Checking reservations put messagebody....OK");
        }  catch (Exception e){
           throw  new IllegalArgumentException();        
        }  
        //if the stage is available for reservation 
        //cart and payment persistence eliminated
        
       if( reservationRepo.isStageTaken(postedReservation.getStageId(), 
           postedReservation.getDateFrom(), postedReservation.getDateTo() ,reservationId) == 0) {            
                       System.out.println("Validating put message body....OK");

           Reservation savedReservation = reservationRepo.save(postedReservation);
                       System.out.println("Saving put messagebody....OK");
           return new ReservationResponse(
                            savedReservation.getId(),
                            savedReservation.getLakeId(),
                            savedReservation.getStageId(),
                            savedReservation.getUserId(),
                            savedReservation.getDateFrom(),
                            savedReservation.getDateTo(),
                            savedReservation.getReservationStatus(),
                            new ArrayList<OrderedItem>(),
                            null,
                            (userRepo.findById(savedReservation.getUserId()) ).get().getDisplayName()
                        ); 
        
       } else {
            throw new StageUnavailableException("The stage is unavailable in the requested period!");
       }
       
    }   
    
    public ReservationResponse getOneById(Long id) 
            throws NoSuchReservationException {
        
        try {
            Reservation foundReservation = reservationRepo.findByIdNative(id);
//            ArrayList<OrderedItem> foundOrders = orderRepo.findOrderedServiceByReservationIdNative(id);
//            Payment foundPayment = paymentRepo.findPaymentByReservationIdNative(id);
            
            return new ReservationResponse(
                    foundReservation.getId(),
                    foundReservation.getLakeId(),
                    foundReservation.getStageId(),
                    foundReservation.getUserId(),
                    foundReservation.getDateFrom(),
                    foundReservation.getDateTo(),
                    foundReservation.getReservationStatus(),
                    new ArrayList<OrderedItem>(),
                    null,
                    (userRepo.findById(foundReservation.getUserId()) ).get().getDisplayName()
                ); 

        } catch (Exception e) {
            throw new NoSuchReservationException("The requested reservation is not found! No data retrieved");
        }        
        
    }

    public ArrayList<ReservationResponse> getAllByQuery(
            @Nullable Long lakeId, @Nullable Long stageId,@Nullable Long userId, 
            @Nullable Date dateFrom, @Nullable Date dateTo, @Nullable String status) {

            ArrayList<Reservation> reservations = new  ArrayList<>();
            ArrayList<ReservationResponse> responseList = new ArrayList<>();
//            Payment payment=null;
//            ArrayList<OrderedItem> orderedServices= new ArrayList<>();                

            try {
                reservations = reservationRepo.getReservationsByQuery(lakeId, stageId, userId, dateFrom, dateTo, status);
                if (reservations.isEmpty()) {
                    //throw new NoSuchReservationException("No matching reservations found by this query.");
                    return new ArrayList<>();
                }
                for(Reservation reservation:reservations){
//                    orderedServices = orderRepo.findOrderedServiceByReservationIdNative(reservation.getId());
//                    payment = paymentRepo.findPaymentByReservationIdNative(reservation.getId());
                    ReservationResponse response = new ReservationResponse(
                        reservation.getId(),
                        reservation.getLakeId(),
                        reservation.getStageId(),
                        reservation.getUserId(),    
                        reservation.getDateFrom(),
                        reservation.getDateTo(),
                        reservation.getReservationStatus() ,
                        new ArrayList<OrderedItem>(),
                        null,
                        (userRepo.findById(reservation.getUserId()) ).get().getDisplayName()
                    ); 
                    responseList.add(response);
                    //if there was an exception it throws it further
                } 

            } catch (Exception e) {
                e.printStackTrace(); 
            }
            return responseList;
        }   
    }
    
