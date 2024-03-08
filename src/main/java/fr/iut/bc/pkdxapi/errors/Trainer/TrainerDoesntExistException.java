package fr.iut.bc.pkdxapi.errors.Trainer;

import org.springframework.http.HttpStatus;

import fr.iut.bc.pkdxapi.errors.APIException;

public class TrainerDoesntExistException extends APIException {
    
    public TrainerDoesntExistException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
