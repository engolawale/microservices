/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.models;

/**
 *
 * @author Olawale Ogunbayo
 */
public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
     public void setMessage(String message) {
        this.message = message;;
    }
    
    
}
