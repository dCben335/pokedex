package fr.iut.bc.pkdxapi.errors.User;

import org.springframework.http.HttpStatus;

import fr.iut.bc.pkdxapi.errors.APIException;


public class UserAlreadyExistException extends APIException {

    public UserAlreadyExistException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}