/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;
import io.swagger.domain.Reservation;
import io.swagger.domain.ReservationStatusEnum;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author huba.tanczos
 */

public interface ReservationsRepository extends JpaRepository<Reservation, Long>{

    @Procedure(procedureName = "reservationsbycriteria")
    ArrayList<Reservation> reservationsbycriteriaProcedureName(
            Long pUserId, 
            Long pLakeId,
            Long pStageId,
            Date pDateFrom,
            Date pDateTo,
            ReservationStatusEnum pStatus
        );

    @Query(value = "SELECT * FROM RESERVATIONS R WHERE R.ID = ?1",
            nativeQuery = true)   
    Reservation findByIdNative(Long id);
    
    

}
