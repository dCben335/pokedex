package fr.iut.bc.pkdxapi.models.Trainer.request;

public class TrainerRequest {
    private String imgUrl;
    private String trainerName;


    public TrainerRequest(String username, String imgUrl, String trainerName) {
        this.imgUrl = imgUrl;
        this.trainerName = trainerName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTrainerName() {
        return trainerName;
    }
}
