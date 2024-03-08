package fr.iut.bc.pkdxapi.models.Trainer;

import java.util.List;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;


@TypeAlias("Trainer")
public class TrainerData extends Trainer {
    
    @Id
    private ObjectId id;

    private Date createdDate;

    public TrainerData(String username, String imgUrl, String trainerName, List<String> pkmnSeen, List<String> pkmnCaught) {
        super(username, imgUrl, trainerName, pkmnSeen, pkmnCaught);
        createdDate = new Date(System.currentTimeMillis());
        id = ObjectId.get();
    }

    public String getId() {
        return this.id.toHexString();
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }
}
