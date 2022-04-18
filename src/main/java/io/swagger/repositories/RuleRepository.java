/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;


import io.swagger.domain.RuleOfConduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  RuleRepository extends JpaRepository<RuleOfConduct, Long> {

    @Query(
        value = "SELECT * FROM RULES_OF_CONDUCT R WHERE R.COMPANY_ID = ?1", 
        nativeQuery = true)
    ArrayList<RuleOfConduct> findRuleByCompanyIdNative(Long companyId); 
    
    @Query(
        value = "SELECT * FROM RULES_OF_CONDUCT R WHERE R.COMPANY_ID ?1 AND R.LAKE_ID = ?2", 
        nativeQuery = true)
    ArrayList<RuleOfConduct> findRuleByCompanyAndLakeIdNative(Long companyId,Long lakeId); 
}