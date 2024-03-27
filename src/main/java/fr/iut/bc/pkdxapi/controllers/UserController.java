package fr.iut.bc.pkdxapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.bc.pkdxapi.models.AuthRequest;
import fr.iut.bc.pkdxapi.models.AuthResponse;
import fr.iut.bc.pkdxapi.models.User.UserDTO;
import fr.iut.bc.pkdxapi.models.User.UserResponse;
import fr.iut.bc.pkdxapi.models.User.UserStatusRequest;
import fr.iut.bc.pkdxapi.services.UserDataService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class UserController {
    UserDataService userDataService;

    public UserController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @PostMapping("/register")
    public AuthResponse register(@RequestBody UserDTO userDTO) {        
        AuthResponse authRequest = userDataService.register(userDTO);
        return authRequest;
    }

    @PutMapping("/admin")
    public UserResponse changeStatutAdmin(@RequestBody UserStatusRequest userStatusRequest) {
        return userDataService.changeUserStatus(userStatusRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest userDTO) {
        return userDataService.login(userDTO);
    }

    @GetMapping("/me")
    public UserResponse me(HttpServletRequest request) {
        return userDataService.getUserLogedData(request); 
    }
    
    @GetMapping("/logged")
    public ResponseEntity<?> logged() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
