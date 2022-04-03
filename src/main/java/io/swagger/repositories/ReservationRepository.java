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
import org.springframework.data.jpa.repository.query.Procedure;

/**
 *
 * @author huba.tanczos
 */

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //stored procedure for custom queries
    @Procedure(name = "Reservation.getReservationsByQuery")
    public ArrayList<Reservation> getReservationsByQuery
        (Long pLakeId, Long pStageId, Long pUserId, 
                Date pDateFrom, Date pDateTo, String pStatus);
        
    //custom findById to overwrite 
    @Query(value = "SELECT * FROM RESERVATIONS R WHERE R.ID = ?1",
            nativeQuery = true)   
    Reservation findByIdNative(Long id);
    
    @Query(value = "select (case count(*) when 0 then true else false end ) from reservations r \n" +
            "where 1=1\n" +
            "and r.stage_id = ?1\n" +
            "and \n" +
            "	( r.date_from >= ?2 and r.date_to >= ?3)\n" +
            "	or\n" +
            "	(r.date_from <=?2 and r.date_to >= ?3)\n" +
            "	or\n" +
            "	(r.date_from <=?2 and r.date_to <= ?3)\n" +
            ")",
            nativeQuery = true)   
    Boolean isStageAvailable(Long pId, Date pDateFrom, Date pDateTo);    

}
