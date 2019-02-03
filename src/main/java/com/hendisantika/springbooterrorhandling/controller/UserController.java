package com.hendisantika.springbooterrorhandling.controller;

import com.hendisantika.springbooterrorhandling.dto.UserDTO;
import com.hendisantika.springbooterrorhandling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-error-handling
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-03
 * Time: 08:54
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Returns the user representation for an username.
     *
     * @param username the username, used for lookup.
     * @return the user dto for the username.
     */
    @RequestMapping("/user/{username}")
    public UserDTO findUser(@PathVariable("username") String username) {
        return userService.findUserByName(username);
    }
}