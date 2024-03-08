package fr.iut.bc.pkdxapi.models.Pkmn;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("PkmnRegion")
public class PkmnRegion {
    private String regionName;
    private String regionPokedexNumber;

    public PkmnRegion(String regionName, String regionPokedexNumber) {
        this.regionName = regionName;
        this.regionPokedexNumber = regionPokedexNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getRegionPokedexNumber() {
        return regionPokedexNumber;
    }
}
