package com.hendisantika.springbooterrorhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Username not found.")
public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String message) {
        super(message);
    }
}