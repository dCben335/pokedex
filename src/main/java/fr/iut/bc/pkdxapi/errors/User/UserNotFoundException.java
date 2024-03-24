package fr.iut.bc.pkdxapi.errors.User;

import org.springframework.http.HttpStatus;

import fr.iut.bc.pkdxapi.errors.APIException;

public class UserNotFoundException extends APIException {
    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
