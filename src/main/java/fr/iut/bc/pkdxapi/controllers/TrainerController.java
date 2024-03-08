package fr.iut.bc.pkdxapi.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.iut.bc.pkdxapi.models.Trainer.TrainerData;
import fr.iut.bc.pkdxapi.models.Trainer.request.TrainerRequest;
import fr.iut.bc.pkdxapi.services.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @PostMapping("")
    public ResponseEntity<TrainerData> createTrainer(@RequestBody TrainerRequest trainer) {    
        TrainerData trainerData =  trainerService.createTrainer(trainer);

        return new ResponseEntity<TrainerData>(trainerData, HttpStatus.CREATED);
    }

    @PutMapping("")
    public void updateTrainer(
        ObjectId id, 
        Optional<String> imgUrl, 
        Optional<String> trainerName, 
        Optional<String> pkmnSeen, 
        Optional<String> pkmnCaught
    ) {
        trainerService.updateTrainer(
            id, 
            imgUrl, 
            trainerName, 
            pkmnSeen, 
            pkmnCaught
        );
    }

    @GetMapping("")
    public void getTrainerByIdOrUserName(
        @RequestParam(required = false) Optional<ObjectId> id,
        @RequestParam(required = false) Optional<String> name
    ) {
        if (id.isPresent()) {
            trainerService.getTrainerById(id.get());
        }

        trainerService.getTrainerByUsername(name.get());
    }


}
