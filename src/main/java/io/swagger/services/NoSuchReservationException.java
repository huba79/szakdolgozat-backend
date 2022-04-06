/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.services;

/**
 *
 * @author huba
 */
public class NoSuchReservationException extends RuntimeException {
    NoSuchReservationException(String errorMessage){
        super(errorMessage);
    }
    
}
