package fr.iut.bc.pkdxapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.iut.bc.pkdxapi.errors.UserAlreadyExistException;
import fr.iut.bc.pkdxapi.models.User.UserDTO;
import fr.iut.bc.pkdxapi.models.User.UserData;
import fr.iut.bc.pkdxapi.repositories.UserRepository;


@Service
public class UserDataService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository repository;

    public UserDataService(UserRepository repository) {
        this.repository = repository;
        
    }


    public void register(UserDTO userData) {
        if (userExists(userData.getLogin())) {
            throw new UserAlreadyExistException("User already exists.");
        }

        String encodedPassword = passwordEncoder.encode(userData.getPassword());
        
        UserData user = new UserData(
            userData.getLogin(), 
            encodedPassword, 
            userData.getIsAdmin()
        );

        repository.insert(user);    
    }

    public boolean userExists(String login) {
        return repository.findById(login).isPresent();
    }

}
