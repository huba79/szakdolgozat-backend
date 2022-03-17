/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.swagger.domain.ServicePrice;
import java.util.ArrayList;
import java.util.Optional;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  ServicePriceRepository extends JpaRepository<ServicePrice, Long> {

    Optional<ServicePrice> findServicePriceById(Long id);
    ArrayList<ServicePrice> findall();
        
}