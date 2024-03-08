package fr.iut.bc.pkdxapi.errors.Trainer;

import org.springframework.http.HttpStatus;

import fr.iut.bc.pkdxapi.errors.APIException;

public class TrainerAlreadyExist extends APIException {
        
    public TrainerAlreadyExist(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
