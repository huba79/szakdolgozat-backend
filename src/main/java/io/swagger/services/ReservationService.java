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
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huba
 */
    @Service
    public class ReservationService {
 

    @Autowired 
    ReservationRepository reservationRepo;
    @Autowired
    OrderedItemRepository orderRepo; 
    @Autowired
    PaymentRepository paymentRepo;
    @Autowired 
    EntityManager em;
        

    public ReservationResponse addReservation(ReservationMessage rMessage) 
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
       if( reservationRepo.isStageAvailable(postedReservation.getStageId(), 
            postedReservation.getDateFrom(), postedReservation.getDateTo() ) ) {            
            
           Reservation savedReservation = new Reservation();
           Payment savedPayment =  new Payment();           
           ArrayList<OrderedItem> savedOrders = new ArrayList<>();  

            //if there are some ordered items, we can save the reservation
            if( !postedOrders.isEmpty()){

                try{
                    savedReservation = reservationRepo.save(postedReservation);
                    for (OrderedItem ordered:postedOrders){
                        ordered.setReservationId(savedReservation.getId());
                    }
                savedReservation = reservationRepo.save(postedReservation);
                savedOrders = (ArrayList) orderRepo.saveAll(postedOrders);
                
                    if( postedPayment!=null){
                        //if there is some payment info then let's try to save it too
                        postedPayment.setReservationId(savedReservation.getId());
                        savedPayment = paymentRepo.save(postedPayment);
                    } else savedPayment = null;
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
                            savedPayment
                        ); 
                    
                } catch (IllegalArgumentException  e) {
                    throw new IllegalArgumentException("Corrupted or incomplete message! Request rejected.");
                }   
                
            } else throw new EmptyCartException("Empty order list! Request rejected.");   
        
       }
       throw new StageUnavailableException("The stage is unavailable in the requested period! Request rejected.");
    }
    
     public ReservationResponse updateReservation(Long reservationId, ReservationMessage rMessage) 
            throws StageUnavailableException, IllegalArgumentException, NoSuchReservationException {
        
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
       if( reservationRepo.isStageAvailable(postedReservation.getStageId(), 
           postedReservation.getDateFrom(), postedReservation.getDateTo() ) ) {            
           
           //TODO treat update cases
           Reservation foundReservation = reservationRepo.findByIdNative(reservationId);
           
           if(foundReservation == null ) {
               throw new NoSuchReservationException("Reservation not found! Request rejected.");
           }
           
           Reservation savedReservation = new Reservation();
           Payment savedPayment =  new Payment();           
           ArrayList<OrderedItem> savedOrders = new ArrayList<>();  

            //if there are some ordered items, we can save the reservation
            if( !postedOrders.isEmpty()){

                try{    
                    //enforcing update
                    //ugly solution, need to learn more about JPA save method

                    postedReservation.setId(foundReservation.getId());                    
                    savedReservation = reservationRepo.save(postedReservation);
                    
                        for(OrderedItem order:postedOrders){
                            orderRepo.deleteById(order.getId());
                        }                    
                    
                        savedOrders = (ArrayList) orderRepo.saveAll(postedOrders);
                
                    if( postedPayment!=null){
                        //if there is some payment info then let's try to save it too
                        savedPayment = paymentRepo.save(postedPayment);
                    } else savedPayment = null;
                    
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
                            savedPayment
                        ); 
                    
                } catch (IllegalArgumentException  e) {
                    throw new IllegalArgumentException("Corrupted or incomplete message!");
                }   
                
            } else throw new EmptyCartException("Empty order list!");   
        
       }
       throw new StageUnavailableException("The stage is unavailable in the requested period!");
    }   
    
    public ReservationResponse getReservationById(Long id) 
            throws NoSuchReservationException {
        
        try {
            Reservation foundReservation = reservationRepo.findByIdNative(id);
            ArrayList<OrderedItem> foundOrders = orderRepo.findOrderedServiceByReservationIdNative(id);
            Payment foundPayment = paymentRepo.findPaymentByReservationIdNative(id);
            
            return new ReservationResponse(
                    foundReservation.getId(),
                    foundReservation.getLakeId(),
                    foundReservation.getStageId(),
                    foundReservation.getUserId(),
                    foundReservation.getDateFrom(),
                    foundReservation.getDateTo(),
                    foundReservation.getReservationStatus(),
                    foundOrders,
                    foundPayment
                ); 

        } catch (Exception e) {
            throw new NoSuchReservationException("The requested reservation is not found! No data retrieved");
        }        
        
    }
}
    
