package com.hendisantika.springbooterrorhandling.service;

import com.hendisantika.springbooterrorhandling.dto.UserDTO;
import com.hendisantika.springbooterrorhandling.exception.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-error-handling
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-03
 * Time: 08:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    /**
     * Method to demonstrate a fake user lookup.
     *
     * @param username The username... There is just an admin account.
     * @return a DTO representation of an user.
     */
    public UserDTO findUserByName(String username) {

        // We just have an administrator account. Nothing else. ;)
        if ("admin".equals(username)) {
            return new UserDTO(username);
        }

        throw new UsernameNotFoundException(String.format("Username not found. username=%s", username));
    }
}