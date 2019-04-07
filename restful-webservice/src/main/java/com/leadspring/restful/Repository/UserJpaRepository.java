/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.Repository;

import com.leadspring.restful.models.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olawale Ogunbayo
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer>{

   //public User saveUser(User user);

  //  public User deleteById(int userId);

 //   public User findUser(int userId) ;
    
 //   public List<User> findAll();
}
