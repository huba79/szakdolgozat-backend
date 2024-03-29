/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.swagger.domain.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  CompanyRepository extends JpaRepository<Company, Long> {

        @Query(
            value = "SELECT * FROM COMPANIES C WHERE C.ID = ?1", 
            nativeQuery = true)
    Company getCompanyById(Long id);
}