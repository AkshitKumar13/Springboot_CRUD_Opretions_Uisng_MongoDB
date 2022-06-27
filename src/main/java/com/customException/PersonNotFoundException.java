package com.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Product not found exception.
 */
@SuppressWarnings("checkstyle:TypeName")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message the message
     */

    public PersonNotFoundException(String message) {
        super(message);
    }
}