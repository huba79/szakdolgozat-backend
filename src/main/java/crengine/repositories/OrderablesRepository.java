/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crengine.domain.Service;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface OrderablesRepository extends JpaRepository<Service, Long> {

    Optional<Service> findServiceById(Long id);
    
    @Query(
      value = "SELECT * FROM SERVICES S WHERE S.LAKE_ID = ?1", 
      nativeQuery = true)
    ArrayList<Service> findServiceByLakeIdNative( Long id);
    
    //persze meg kell oldani a mentest is
    @Query(
      value = "SELECT s.price_per_unit FROM SERVICES S WHERE S.ID = ?1", 
      nativeQuery = true)
    Double findPriceByServiceIdNative( Long id);
}