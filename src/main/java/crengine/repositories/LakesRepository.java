/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crengine.domain.Lake;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  LakesRepository extends JpaRepository<Lake, Long> {

    Optional<Lake> findLakeById(Long id);
        @Query(
            value = "SELECT * FROM LAKES L WHERE L.COMPANY_ID = ?1 ORDER BY ID", 
            nativeQuery = true)
    ArrayList<Lake> findLakeByCompanyId(Long id); 
    
}
