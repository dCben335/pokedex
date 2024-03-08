package fr.iut.bc.pkdxapi.models.Pkmn;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


@Document("Pkmn")
@TypeAlias("PkmnData")
public class PkmnData extends Pkmn {
    
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;

    public PkmnData(String name, String description, List<PkmnTypes> types, List<PkmnRegion> regions, String imgUrl) {
        super(name, description, types, regions, imgUrl);  
        id = ObjectId.get();
    }

    public String getId() {
        return this.id.toHexString();
    }

}
