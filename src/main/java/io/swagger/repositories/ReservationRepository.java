/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;
import io.swagger.domain.Reservation;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author huba.tanczos
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
 // igy mukszik    
    @Query(
        value="select * from reservations r\n" +
            "where 1=1\n" +
            "and (?1 is null or r.lake_id = ?1)\n" +
            "and (?2 is null or r.stage_id = ?2)\n" +
            "and (?3 is null or r.user_id = ?3)\n" +
            "and (?4 is null or r.date_from >= ?4)\n" +
            "and (?5 is null or r.date_to <= ?5)\n" +
            "and (?6 is null or r.reservation_status = ?6)",
        nativeQuery=true)    
   // @Procedure(name = "Reservation.getReservationsByQuery")
   //stored procedure for custom queries 
    public ArrayList<Reservation> getReservationsByQuery
        (Long pLakeId, Long pStageId, Long pUserId, 
                Date pDateFrom, Date pDateTo, String pStatus);
        
    //custom findById to overwrite 
    @Query(value = "SELECT * FROM RESERVATIONS R WHERE R.ID = ?1",
            nativeQuery = true)   
    Reservation findByIdNative(Long id);
    
    @Query(value = 
            "select count(*)  from reservations r \n" +
            "where 1=1 \n" +
            "and r.id <> ?4 \n" +
            "and r.stage_id = ?1 \n" +
            "and (\n" +                
            "	( (r.date_from between ?2 and ?3) and r.date_to >= ?3) \n" +
            "	or \n" +
            "	(r.date_from <= ?2 and (r.date_to between ?2 and ?3)) \n" +
            "	or \n" +
            "	(r.date_from <= ?2 and r.date_to >= ?3) \n" +
            ")",
            nativeQuery = true)   
    int isStageTaken(Long pStageId, Date pDateFrom, Date pDateTo,Long reservationID); 
    

}
