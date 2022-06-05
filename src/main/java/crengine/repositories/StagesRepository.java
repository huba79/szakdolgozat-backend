/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crengine.domain.Stage;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface StagesRepository extends JpaRepository<Stage, Long> {

    Optional<Stage> findStageById(Long id);
        @Query(
            value = "SELECT * FROM STAGES S WHERE S.LAKE_ID = ?1", 
            nativeQuery = true)
    ArrayList<Stage> findStageByLakeIdNative(Long id);
    
    @Query(
            value = "SELECT * FROM STAGES S WHERE S.LAKE_ID = ?1", 
            nativeQuery = true)
    ArrayList<ArrayList<Stage>> stageScheduleForInterval(Long LakeId);
  
    
}