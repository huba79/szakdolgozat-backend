/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.swagger.domain.Stage;
import java.util.ArrayList;
import java.util.Optional;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    Optional<Stage> findStageById(Long id);
    ArrayList<Stage> findStageByLakeId(Long id);
}