package fr.iut.bc.pkdxapi.models.Pkmn.requests;

import fr.iut.bc.pkdxapi.models.Pkmn.PkmnRegion;

public class PkmnRegionRequest {
    private String name;
    private PkmnRegion region;

    public PkmnRegionRequest(String name, PkmnRegion region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public PkmnRegion getRegion() {
        return region;
    }
}
