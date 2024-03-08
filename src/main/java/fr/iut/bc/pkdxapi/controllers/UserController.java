package fr.iut.bc.pkdxapi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.bc.pkdxapi.models.User.UserDTO;
import fr.iut.bc.pkdxapi.services.UserDataService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/users")
public class UserController {
    UserDataService userDataService;

    public UserController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {        
        userDataService.register(userDTO);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("User registered successfully.");

    }

    @GetMapping("/login")
    public ResponseEntity<String> isLogged() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("User logged in successfully.");
    }
}
