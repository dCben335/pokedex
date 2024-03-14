package fr.iut.bc.pkdxapi.models.Pkmn.requests;

import java.util.List;

import fr.iut.bc.pkdxapi.models.Pkmn.PkmnTypeDto;
import fr.iut.bc.pkdxapi.models.Pkmn.PkmnTypes;

public class PkmnTypesResponse {
    private List<PkmnTypeDto> types;
    private int count;

    public PkmnTypesResponse(List<PkmnTypeDto> types) {
        this.types = types;
        this.count = types.size();
    }

    public List<PkmnTypeDto> getTypes() {
        return types;
    }

    public int getCount() {
        return count;
    }
}
