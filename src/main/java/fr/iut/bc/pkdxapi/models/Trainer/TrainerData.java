package fr.iut.bc.pkdxapi.models.Trainer;

import java.sql.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

public class TrainerData extends Trainer {
    
    @Id
    private ObjectId id;

    @CreatedDate
    private Date createdDate;

    public TrainerData(String username, String imgUrl, String trainerName, List<String> pkmnSeen, List<String> pkmnCaught) {
        super(username, imgUrl, trainerName, pkmnSeen, pkmnCaught);
        id = ObjectId.get();
    }

    public String getId() {
        return this.id.toHexString();
    }
}
