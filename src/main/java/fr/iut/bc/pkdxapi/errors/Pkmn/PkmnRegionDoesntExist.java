package fr.iut.bc.pkdxapi.errors.Pkmn;


import org.springframework.http.HttpStatus;
import fr.iut.bc.pkdxapi.errors.APIException;

public class PkmnRegionDoesntExist extends APIException {
    
    public PkmnRegionDoesntExist(String name) {
        super(HttpStatus.NOT_FOUND , "Pkmn region " + name + " doesn't exist.");
    }
}
