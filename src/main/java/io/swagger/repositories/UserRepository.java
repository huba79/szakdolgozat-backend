/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.swagger.domain.User;
import java.util.Optional;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
        
    Optional<User> findUserById(Long id);
    ArrayList<User> findUserByEmailAddress(String pEmail);
    User findUserBySessionId(String uuid);
      
}