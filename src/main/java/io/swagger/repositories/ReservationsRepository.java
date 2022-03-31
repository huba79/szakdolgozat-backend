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
import org.springframework.lang.Nullable;

/**
 *
 * @author huba.tanczos
 */

public interface ReservationsRepository extends JpaRepository<Reservation, Long>{

    @Procedure(procedureName = "reservationsbycriteria")
    ArrayList<Reservation> reservationsbycriteriaProcedureName(
            @Nullable Long pUserId, 
            @Nullable Long pLakeId,
            @Nullable Long pStageId,
            @Nullable Date pDateFrom,
            @Nullable Date pDateTo,
            @Nullable ReservationStatusEnum pStatus
        );

    @Query(value = "SELECT * FROM RESERVATIONS R WHERE R.ID = ?1",
            nativeQuery = true)   
    Reservation findByIdNative(Long id);
    
    

}
