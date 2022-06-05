/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crengine.api;

import crengine.domain.Payment;
import crengine.domain.Reservation;
import crengine.domain.User;
import crengine.messages.PaymentResponse;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import crengine.repositories.CompaniesRepository;
import crengine.repositories.PaymentsRepository;
import crengine.repositories.ReservationsRepository;
import crengine.repositories.UsersRepository;

/**
 *
 * @author huba
 */
@RestController
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")

public class PaymentController implements PaymentApi{
    @Autowired PaymentsRepository paymentRepo;
    @Autowired ReservationsRepository reservationsRepo;     
    @Autowired HttpServletRequest request;
    @Autowired CompaniesRepository companyRepo;
    @Autowired UsersRepository usersRepo;

    @Override
    public ResponseEntity<PaymentResponse> getPaymentByReservationId(Long reservationId) {
        RequestValidator validator = new RequestValidator(request,usersRepo,companyRepo); 
        if(  validator.isApiKeyValid() && validator.isAuthorized() && validator.acceptsJson() ) {
                
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
