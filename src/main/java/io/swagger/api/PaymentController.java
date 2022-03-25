/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.api;

import io.swagger.domain.Payment;
import io.swagger.domain.User;
import io.swagger.messages.PaymentResponse;
import io.swagger.repositories.PaymentRepository;
import io.swagger.repositories.UserRepository;
import java.util.ArrayList;
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
    @Autowired
    PaymentRepository paymentRepo;
    @Autowired
    UserRepository usersRepo;    
    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseEntity<PaymentResponse> getPaymentById(Long id) {
        RequestValidator validator = new RequestValidator(request); 
        if(  validator.hasValidHeader() && validator.isAuthorized() && validator.acceptsJson() ) {
                
                Payment payment = paymentRepo.findPaymentById(id).get();
                if(payment !=null) {
                    User user = usersRepo.findById(id).get();
                    PaymentResponse paymentResponse = new PaymentResponse(
                          payment.getId(),
                          payment.getReservation(),
                            payment.getPaidWith(),
                            payment.getAmount(),
                            payment.getPaidOn(),
                            (user ==null)? "GUEST" : user.getDisplayName(),
                            payment.getPaymentStatus()
                    );
                    return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
                } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  
    }
    //implementalni!
    @Override
    public ResponseEntity<PaymentResponse> getPaymentByReservationId(Long reservationId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //implementalni!
    @Override
    public ResponseEntity<ArrayList<PaymentResponse>> getAllPayments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
