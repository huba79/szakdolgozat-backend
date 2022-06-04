/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;


import crengine.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  PaymentRepository extends JpaRepository<Payment, Long> {
    
    Optional<Payment> findPaymentById(Long id);
   
    @Query(
      value = "SELECT * FROM PAYMENTS P WHERE P.RESERVATION_ID = ?1", 
      nativeQuery = true)
    Payment findPaymentByReservationIdNative( Long id);
    
    //persze meg kell oldani a mentest is
}