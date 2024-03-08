package fr.iut.bc.pkdxapi.errors.Pkmn;

import org.springframework.http.HttpStatus;
import fr.iut.bc.pkdxapi.errors.APIException;

public class PkmnRegionAlreadyExistException extends APIException {
    
    public PkmnRegionAlreadyExistException(String name) {
        super(HttpStatus.CONFLICT ,"Pkmn region " + name + " already exist.");
    }
}
