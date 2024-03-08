package fr.iut.bc.pkdxapi.models.Pkmn;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.TypeAlias;

import fr.iut.bc.pkdxapi.models.Pkmn.Types.PkmnTypes;

@TypeAlias("Pkmn")
public class Pkmn {

    private String name;
    private String description;
    private List<PkmnTypes> types;
    private List<PkmnRegion> regions;
    private String imgUrl;

    public Pkmn(String name, String description, List<PkmnTypes> types, List<PkmnRegion> regions, String imgUrl) {
        this.name = name;
        this.description = description;
        this.types = types;
        this.regions = regions;
        this.imgUrl = imgUrl;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<PkmnTypes> getTypes() {
        return types;
    }

    public List<PkmnRegion> getRegions() {
        return regions;
    }

    public String getImgUrl() {
        return imgUrl;
    }



    public String setName(String name) {
        return this.name = name;
    }
  
    public String setDescription(String description) {
        return this.description = description;
    }

    public List<PkmnTypes> setTypes(Optional<PkmnTypes> typeOne, Optional<PkmnTypes> typeTwo) {
        if (typeOne.isPresent() && typeTwo.isPresent()) {
            return this.types = List.of(typeOne.get(), typeTwo.get());
        }

        if (typeOne.isPresent()) {
            return this.types = List.of(typeOne.get());
        }

        if (typeTwo.isPresent()) {
            return this.types = List.of(typeTwo.get());
        }

        return this.types;
        
    }

    public List<PkmnRegion> setRegions(List<PkmnRegion> regions) {
        return this.regions = regions;
    }

    public String setImgUrl(String imgUrl) {
        return this.imgUrl = imgUrl;
    }
}
