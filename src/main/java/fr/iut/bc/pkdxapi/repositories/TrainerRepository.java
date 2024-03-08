package fr.iut.bc.pkdxapi.repositories;

import fr.iut.bc.pkdxapi.models.Trainer.TrainerData;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface TrainerRepository extends MongoRepository<TrainerData, ObjectId>{
    
    @Query("{username:'?0'}")
    Optional<TrainerData> findByUsername(String username);
}
