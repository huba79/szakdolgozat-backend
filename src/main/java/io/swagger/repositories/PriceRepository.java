/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.swagger.domain.Price;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findPriceById(Long id);
    @Query(
      value = "SELECT * FROM PRICES P WHERE P.LAKE_ID = ?1", 
      nativeQuery = true)
    ArrayList<Price> findPriceByLakeIdNative( Long id);

}