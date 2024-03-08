package fr.iut.bc.pkdxapi.models.Trainer;

import java.util.List;

public class Trainer {
    private String username;
    private String imgUrl;
    private String trainerName;

    private List<String> pkmnSeen;
    private List<String> pkmnCaught;


    public Trainer(String username, String imgUrl, String trainerName, List<String> pkmnSeen, List<String> pkmnCaught) {
        this.username = username;
        this.imgUrl = imgUrl;
        this.trainerName = trainerName;
        this.pkmnSeen = pkmnSeen;
        this.pkmnCaught = pkmnCaught;
    }

    public String getUsername() {
        return username;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<String> getPkmnSeen() {
        return pkmnSeen;
    }

    public List<String> getPkmnCaught() {
        return pkmnCaught;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public void setPkmnSeen(List<String> pkmnSeen) {
        this.pkmnSeen = pkmnSeen;
    }

    public void setPkmnCaught(List<String> pkmnCaught) {
        this.pkmnCaught = pkmnCaught;
    }
}
