package fr.iut.bc.pkdxapi.errors;

import org.springframework.http.HttpStatus;


public class UserAlreadyExistException extends APIException {

    public UserAlreadyExistException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}