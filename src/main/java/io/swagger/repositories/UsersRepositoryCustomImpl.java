/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.repositories;

import io.swagger.domain.users.User;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author huba
 */
public class UsersRepositoryCustomImpl implements UsersRepositoryCustom {
     @PersistenceContext
  private EntityManager em;
  
  @Autowired
  private UsersRepository repository;
  
  @Transactional
  public User save(User user) {
    return repository.save(user);
  }
  @Transactional
  public ArrayList<User> findUserByEmail(String email) {
    
    return (ArrayList) em.createQuery(
    "SELECT c FROM Users c WHERE c.emailAddress = :email")
    .setParameter("email", email)
    .setMaxResults(1)
    .getResultList();    
  }
  
    @Transactional
    public ArrayList<User> modifyUserById(String email) {
    
    return (ArrayList) em.createQuery(
    "SELECT c FROM Users c WHERE c.emailAddress = :email")
    .setParameter("email", email)
    .setMaxResults(1)
    .getResultList();    
  }
//itt kell implementalni a custom interfesz hozzaadott metodusait
    
    @Override
    public int doLogin(String arg0, String arg1) {
        throw new UnsupportedOperationException("Not supported yet."); 
// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int doLogout(Long arg0) {
        throw new UnsupportedOperationException("Not supported yet."); 
// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    

}

