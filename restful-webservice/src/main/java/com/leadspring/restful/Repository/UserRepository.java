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
import org.springframework.stereotype.Component;

/**
 *
 * @author Olawale Ogunbayo
 */
@Component
public class UserRepository {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 5;
    
    static {
        users.add(new User(1, "Adam", "Smith", new Date()));
        users.add(new User(2, "Jack", "Bauer", new Date()));
        users.add(new User(3, "Ousmane", "Dembele", new Date()));
        users.add(new User(4, "Raheem", "Sterling", new Date()));
        users.add(new User(5, "Eden", "Hazard", new Date()));
    }

    public User saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User deleteById(int userId) {
        Iterator iter = users.iterator();
        while (iter.hasNext()) {
            User user = (User) iter.next();
            if (user.getId() == userId) {
                iter.remove();
                return user;
            }
        }
        return null;
    }

    public User findUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }

        return null;
    }

    public List<User> findAll() {
        return users;
    }

}
