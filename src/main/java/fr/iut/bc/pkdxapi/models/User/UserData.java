package fr.iut.bc.pkdxapi.models.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
@TypeAlias("UserData")
public class UserData {

    @Id
    private String login;
    
    private String password;
    private Boolean isAdmin;

    public UserData(String login, String password, Boolean isAdmin) {
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

