/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.api;


import io.swagger.repositories.ReservationsRepository;
import io.swagger.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author huba.tanczos
 */
//Reservation validator: cannot place a reservation for a stage being used in the requested interval
public class NewReservationValidator {
    @Autowired
    ReservationsRepository repository;
    Reservation reservation;
    public NewReservationValidator(Reservation data){
        this.reservation = data;
    }

//    public boolean canBePlaced() {
//        List<Reservation> reservationsList = new ArrayList<>();
//            reservationsList = repository.findByStageIdAndDateFromAndDateToNamedParams(reservation.getStageId(),
//                                reservation.getDateFrom(),reservation.getDateTo());
//        return reservationsList.isEmpty();
//    }

}
