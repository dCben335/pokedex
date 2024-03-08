package fr.iut.bc.pkdxapi.models.Pkmn.requests;

import java.util.List;

import fr.iut.bc.pkdxapi.models.Pkmn.PkmnTypes;

public class PkmnTypesResponse {
    private List<PkmnTypes> types;
    private int count;

    public PkmnTypesResponse(List<PkmnTypes> types) {
        this.types = types;
        this.count = types.size();
    }

    public List<PkmnTypes> getTypes() {
        return types;
    }

    public int getCount() {
        return count;
    }
}
