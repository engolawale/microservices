/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.controllers;

import com.leadspring.restful.models.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Olawale Ogunbayo
 */
@RestController
@RequestMapping("/hello")
public class HelloWordlRestController {

    @Autowired
    private MessageSource messageSource;
    
    
    @GetMapping(path = "/world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }
    
    @GetMapping(path = "/world/path-variable/{name}")
    public HelloWorldBean helloWorld(@PathVariable String name) {
        //return new HelloWorldBean("Hello "+name);
        return new HelloWorldBean(String.format("Hello %s",name));
    }
    
    @GetMapping(path = "/world-internationalized")
    public String helloWorldInternationalized() {
        
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
