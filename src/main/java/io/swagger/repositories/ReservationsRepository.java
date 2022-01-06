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
import org.springframework.data.repository.query.Param;
import org.threeten.bp.OffsetDateTime;

/**
 *
 * @author huba.tanczos
 */

public interface ReservationsRepository extends JpaRepository<Reservation, Long>{

    @Query("SELECT r FROM Reservations r WHERE stageId =:pStageId and (dateTo > :pDateFrom OR dateFrom < :pDateTo )")
    ArrayList<Reservation> findByStageIdAndDateFromAndDateToNamedParams(
            @Param("pStageId") Long pStageId,
            @Param("pDateFrom") Date pDateFrom,
            @Param("pDateTo") Date pDateTo
        );

    @Query("SELECT r FROM Reservations r WHERE r.userId = :pUserId")
    ArrayList<Reservation> findByUserIdNamedParams(
        @Param("pUserId") Long pUserId );

  //      ArrayList<Reservations> getReservationsByFilter(Long id, Integer status, OffsetDateTime dateFrom, OffsetDateTime dateTo);

}
