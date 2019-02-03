package com.hendisantika.springbooterrorhandling.dto;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-error-handling
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-03
 * Time: 08:51
 * To change this template use File | Settings | File Templates.
 */
public class UserDTO implements Serializable {

    private String username;

    public UserDTO() {
        // Empty for deserialization
    }

    public UserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}