package com.hendisantika.springbooterrorhandling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-error-handling
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-02-03
 * Time: 08:53
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class ErrorControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    /**
     * This method handles all known exceptions.
     * @param req the http request.
     * @param resp the http response.
     * @param exception the thrown exception.
     * @return a response with an error entity.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> error(HttpServletRequest req, HttpServletResponse resp, Exception exception) {
        try {
            // Note that this is just an abbreviated version without internal error handling or null checks...
            String errorMessage = (String) req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
            MDC.put("error", errorMessage);

            // The complete Stacktrace should just be logged if there is another cause.
            if (exception.getCause() != null) {
                LOG.warn("An error occured: " + exception.getMessage(), exception);
            } else {
                LOG.warn("An error occured: " + exception.getMessage());
            }

            // Determines an eventually response code and a reason, annotated at the exception class itself.
            Class<?> responseStatusAnnotation = AnnotationUtils.findAnnotationDeclaringClass(ResponseStatus.class, exception.getClass());
            if (responseStatusAnnotation != null && responseStatusAnnotation.getAnnotations().length > 0) {
                ResponseStatus responseStatus = (ResponseStatus) responseStatusAnnotation.getAnnotations()[0];

                if (responseStatus.reason() != null && "".equals(responseStatus.reason()) == false) {
                    return ResponseEntity.status(responseStatus.code()).body(new ErrorDTO(responseStatus.reason()));
                } else {
                    return ResponseEntity.status(responseStatus.code()).body(new ErrorDTO(errorMessage));
                }
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(errorMessage));

        } finally {
            MDC.remove("error");
        }
    }


    /**
     * A representation of an error.
     */
    public static class ErrorDTO {
        private String message;

        public ErrorDTO(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public ErrorDTO setMessage(String message) {
            this.message = message;
            return this;
        }
    }
}