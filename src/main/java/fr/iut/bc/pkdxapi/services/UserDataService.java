package fr.iut.bc.pkdxapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.iut.bc.pkdxapi.errors.UserAlreadyExistException;
import fr.iut.bc.pkdxapi.models.AuthRequest;
import fr.iut.bc.pkdxapi.models.AuthResponse;
import fr.iut.bc.pkdxapi.models.User.UserDTO;
import fr.iut.bc.pkdxapi.models.User.UserData;
import fr.iut.bc.pkdxapi.models.User.UserResponse;
import fr.iut.bc.pkdxapi.repositories.UserRepository;
import fr.iut.bc.pkdxapi.utils.JwtUtil;

@Service
public class UserDataService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    public UserDataService(UserRepository repository) {
        this.repository = repository;
    }

    public AuthResponse register(UserDTO userData) {
        if (userExists(userData.getLogin())) {
            throw new UserAlreadyExistException("User already exists.");
        }
    
        UserData user = new UserData(
            userData.getLogin(),
            passwordEncoder.encode(userData.getPassword()),
            userData.getIsAdmin()
        );
    
        repository.save(user);
        String token = getToken(user.getLogin(), userData.getPassword());
        UserResponse userResponse = new UserResponse(user.getLogin(), user.getIsAdmin());

        return new AuthResponse(token, userResponse);
    }

    public AuthResponse login(AuthRequest authRequest) {
        UserData user = getUserByLogin(authRequest.getLogin());
        if (user == null) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        String token = getToken(authRequest.getLogin(), authRequest.getPassword());
        UserResponse userResponse = new UserResponse(user.getLogin(), user.getIsAdmin());

        return new AuthResponse(token, userResponse);
    }




    private String getToken(String login, String password) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        
        return jwtUtil.generateToken(login);
    }






    public boolean userExists(String login) {
        return repository.findById(login).isPresent();
    }

    public UserData getUserByLogin(String login) {
        return repository.findByLogin(login);
    }
}
