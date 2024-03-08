package fr.iut.bc.pkdxapi.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.bc.pkdxapi.models.Trainer.TrainerData;
import fr.iut.bc.pkdxapi.models.Trainer.request.TrainerRequest;
import fr.iut.bc.pkdxapi.services.TrainerService;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @GetMapping("")
    public TrainerData getTrainerByIdOrUserName() {
        return trainerService.getTrainer();
    }


    @PostMapping("")
    public ResponseEntity<TrainerData> createTrainer(@RequestBody TrainerRequest trainer) {    
        TrainerData trainerData = trainerService.createTrainer(trainer);

        return new ResponseEntity<TrainerData>(
            trainerData, HttpStatus.CREATED
        );
    }

    @DeleteMapping("")
    public void deleteTrainer() {
        trainerService.deleteTrainer();
    }

    @PutMapping("")
    public ResponseEntity<TrainerData> updateTrainer(
        @RequestParam(required = false) Optional<String> imgUrl, 
        @RequestParam(required = false) Optional<String> trainerName
    ) {
        TrainerData trainerData = trainerService.updateTrainer( 
            imgUrl, 
            trainerName
        );

        return new ResponseEntity<TrainerData>(
            trainerData, HttpStatus.CREATED
        );
    }


    @PostMapping("/mark")
    public TrainerData updtateTrainerPokemon(
        @RequestParam(required = true) ObjectId pkmnId,
        @RequestParam(required = true) boolean isCaught
    ) {
        return trainerService.updateTrainerPokemon(pkmnId, isCaught);
    }
    
}
