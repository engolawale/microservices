/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.Repository;

import com.leadspring.restful.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olawale Ogunbayo
 */
@Repository
public interface PostJpaRepository extends JpaRepository<Post, Integer>{

   //public User saveUser(User user);

  //  public User deleteById(int userId);

 //   public User findUser(int userId) ;
    
 //   public List<User> findAll();
}
