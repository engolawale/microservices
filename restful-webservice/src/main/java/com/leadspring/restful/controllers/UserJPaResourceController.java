/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.controllers;

import com.leadspring.restful.exception.UserNotFoundException;
import java.util.Optional;

import com.leadspring.restful.Repository.PostJpaRepository;
import com.leadspring.restful.Repository.UserJpaRepository;
import com.leadspring.restful.Repository.UserRepository;
import com.leadspring.restful.models.Post;
import com.leadspring.restful.models.User;
import java.net.URI;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/jpa")
public class UserJPaResourceController {

    private UserJpaRepository userRepository;
    private PostJpaRepository postRepository;

    @Autowired
    public UserJPaResourceController(UserJpaRepository userRepository, PostJpaRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{userid}")
    public Resource<User> getUser(@PathVariable int userid) {
    	Optional<User> requestedUser = userRepository.findById(userid);
        if (!requestedUser.isPresent()) {
            throw new UserNotFoundException("id-" + userid);
        }
        Resource<User> resource = new Resource<User>(requestedUser.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    
    @PostMapping(path = "/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequestUri().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
   
    @DeleteMapping(path = "/users/{userid}")
    public void deleteUser(@PathVariable int userid) {
        userRepository.deleteById(userid);
         
    }
    
    @GetMapping(path = "/users/{userid}/posts")
    public List<Post> getUserPosts(@PathVariable int userid) {
    	Optional<User> requestedUser = userRepository.findById(userid);
        if (!requestedUser.isPresent()) {
            throw new UserNotFoundException("id-" + userid);
        }
        
        return requestedUser.get().getPosts();
    }
   
    @PostMapping(path = "/users/{userid}/posts")
    public ResponseEntity<?> createPost(@Valid @RequestBody Post post,@Valid @PathVariable int userid) {
    	Optional<User> requestedUser = userRepository.findById(userid);
        if (!requestedUser.isPresent()) {
            throw new UserNotFoundException("id-" + userid);
        }
        User user = requestedUser.get();
        post.setUser(user);
    	Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequestUri().
                path("/{id}").
                buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
