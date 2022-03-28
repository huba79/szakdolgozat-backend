/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.domain.Payment;
import io.swagger.domain.Reservation;
import io.swagger.domain.User;
import io.swagger.messages.PaymentResponse;
import io.swagger.repositories.CompanyRepository;
import io.swagger.repositories.PaymentRepository;
import io.swagger.repositories.ReservationsRepository;
import io.swagger.repositories.UserRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huba
 */
@RestController
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")

public class PaymentController implements PaymentService{
    @Autowired PaymentRepository paymentRepo;
    @Autowired ReservationsRepository reservationsRepo;     
    @Autowired HttpServletRequest request;
    @Autowired CompanyRepository companyRepo;
    @Autowired UserRepository usersRepo;

    @Override
    public ResponseEntity<PaymentResponse> getPaymentByReservationId(Long reservationId) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.hasValidHeader() && validator.isAuthorized() && validator.acceptsJson() ) {
                
                Payment payment = paymentRepo.findPaymentByReservationIdNative(reservationId);
                if(payment !=null) {
                   User user = null;
                   Reservation reservation=null;
                   try {
                         reservation = reservationsRepo.findById(reservationId).get();                        
                         user = usersRepo.findById(reservation.getUserId()).get();
                   } catch(NoSuchElementException ex) {
                       //orphan payment or not linked user
                       if(reservation == null){
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        };
                    } 

                    PaymentResponse paymentResponse = new PaymentResponse(
                        payment.getId(),
                        payment.getReservationId(),
                        payment.getPaidWith(),
                        payment.getAmount(),
                        payment.getPaidOn(),
                        (user ==null)? "GUEST" : user.getDisplayName(),
                        payment.getPaymentStatus()
                    );
                    return new ResponseEntity<>(paymentResponse,HttpStatus.OK);

                
                } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);     }
    //implementalni!

}
