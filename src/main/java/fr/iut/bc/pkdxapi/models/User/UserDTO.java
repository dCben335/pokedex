package fr.iut.bc.pkdxapi.models.User;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("UserDTO")
public class UserDTO {

    private String login;
    
    private String password;
    private Boolean isAdmin;

    public UserDTO(String login, String password, Boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}
