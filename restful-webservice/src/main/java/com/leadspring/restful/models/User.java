/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leadspring.restful.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author Olawale Ogunbayo
 */
@ApiModel(description="user model")
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message="firstName Should be at least 2 characters")
    @ApiModelProperty(notes="minimum of 2 characters")
    @Column(name = "firstName", length = 50) 
    private String firstName;
    @Size(min = 2, message="firstName Should be at least 2 characters")
    @Column(name = "lastName", length = 50) 
    private String lastName;
    @Past(message="Date of birth cannot be in the future")
    @ApiModelProperty(notes="Date of birth cannot be in the future")
    @Column(name = "birthDate") 
    private Date birthDate;
    
    @OneToMany(mappedBy="user")
    private List<Post> posts;

    public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User() {
    }

    public User(int id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + '}';
    }
    

}
