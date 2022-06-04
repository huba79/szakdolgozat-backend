/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crengine.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
        
    Optional<User> findUserById(Long id);
    ArrayList<User> findUserByEmailAddress(String pEmail);
    User findUserBySessionId(String uuid);
        
    @Query(
            value = "SELECT * FROM USERS U WHERE U.EMAIL = ?1 "
                    + "and U.PASSWORD = ?2 "
                    + "AND U.GROUP_NAME = ?3", 
            nativeQuery = true)
    User findUser(String pEmail, String pPassword, String pRole ); 
      
}