package fr.iut.bc.pkdxapi.models.Pkmn.Types;

import java.util.List;

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
