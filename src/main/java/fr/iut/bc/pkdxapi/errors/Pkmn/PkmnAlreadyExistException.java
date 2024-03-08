package fr.iut.bc.pkdxapi.errors.Pkmn;

import org.springframework.http.HttpStatus;

import fr.iut.bc.pkdxapi.errors.APIException;


public class PkmnAlreadyExistException extends APIException {
    
    public PkmnAlreadyExistException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
    
}
