/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;

import io.swagger.domain.OrderedService;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  OrderedServiceRepository extends JpaRepository<OrderedService, Long> {
       
    @Query(
      value = "SELECT * FROM ORDERED_SERVICES OS WHERE OS.RESERVATION_ID = ?1", 
      nativeQuery = true)
    ArrayList<OrderedService> findOrderedServiceByReservationIdNative(Long reservationId);


}