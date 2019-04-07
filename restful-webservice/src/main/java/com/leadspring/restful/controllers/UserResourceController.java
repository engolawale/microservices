/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.controllers;

import com.leadspring.restful.exception.UserNotFoundException;
import com.leadspring.restful.Repository.UserRepository;
import com.leadspring.restful.models.User;
import java.net.URI;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Olawale Ogunbayo
 */
@RestController
@RequestMapping("/user")
public class UserResourceController {

    private UserRepository userRepository;

    public UserResourceController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{userid}")
    public Resource<User> getUser(@PathVariable int userid) {
        User requestedUser = userRepository.findUser(userid);
        if (requestedUser == null) {
            throw new UserNotFoundException("id-" + userid);
        }
        Resource<User> resource = new Resource<User>(requestedUser);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    
    @PostMapping(path = "/user")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.saveUser(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequestUri().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

   
}
