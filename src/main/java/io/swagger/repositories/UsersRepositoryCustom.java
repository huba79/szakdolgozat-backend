/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.swagger.repositories;

import io.swagger.domain.users.User;

/**
 *
 * @author huba
 */
public interface UsersRepositoryCustom{
    
    //custom fejlecei amiket majd a repo CLASS fog definialni (UsersRepositoryCustomImpl)
    public int doLogout(Long pUserid);
    public int doLogin(String pEmail, String pPassword);
}
