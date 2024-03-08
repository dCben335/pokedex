package fr.iut.bc.pkdxapi.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.iut.bc.pkdxapi.errors.Trainer.TrainerAlreadyExist;
import fr.iut.bc.pkdxapi.errors.Trainer.TrainerDoesntExistException;
import fr.iut.bc.pkdxapi.models.Trainer.TrainerData;
import fr.iut.bc.pkdxapi.models.Trainer.request.TrainerRequest;
import fr.iut.bc.pkdxapi.repositories.TrainerRepository;

@Service
public class TrainerService {

    private TrainerRepository repository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.repository = trainerRepository;
    }
    

    public TrainerData getTrainerById(ObjectId id) {
        TrainerData trainerData =  repository.findById(id).get();
        if (trainerData == null) {
            throw new TrainerDoesntExistException("Trainer with id " + id + " doesn't exist");
        }

        return trainerData;
    }

    public TrainerData getTrainerByUsername(String username) {
        TrainerData trainerData = repository.findByUsername(username).get();
        if (trainerData == null) {
            throw new TrainerDoesntExistException("Trainer with username " + username + " doesn't exist");
        }

        return trainerData;
    }


    public TrainerData createTrainer(TrainerRequest trainer) {
        String username = getUserName();

        if (trainerExist(username)) {
            throw new TrainerAlreadyExist("Trainer with username " + username + " already exist");
        }


        TrainerData trainerData = new TrainerData(
            username, 
            trainer.getImgUrl(), 
            trainer.getTrainerName(), 
            null,
            null
        );

        return repository.save(trainerData);   
    }

    public TrainerData updateTrainer (
        ObjectId id, 
        Optional<String> imgUrl, 
        Optional<String> trainerName, 
        Optional<String> pkmnSeen, 
        Optional<String> pkmnCaught
    ) {
        TrainerData trainerData = repository.findById(id).get();

        return repository.save(trainerData);
    }


    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return username;
    }

    public boolean trainerExist(String username) {
        return repository.findByUsername(username).isPresent();
    }
}
