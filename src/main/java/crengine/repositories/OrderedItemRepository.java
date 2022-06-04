/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;

import crengine.domain.OrderedItem;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
       
    @Query(
      value = "SELECT * FROM ORDERED_SERVICES OS WHERE OS.RESERVATION_ID = ?1", 
      nativeQuery = true)
    ArrayList<OrderedItem> findOrderedServiceByReservationIdNative(Long reservationId);

    @Modifying(flushAutomatically = true)
    @Query(
      value = "DELETE FROM ORDERED_SERVICES OS WHERE OS.RESERVATION_ID = ?1", 
      nativeQuery = true)
    Boolean deleteByReservationIdNative(Long reservationId);
}