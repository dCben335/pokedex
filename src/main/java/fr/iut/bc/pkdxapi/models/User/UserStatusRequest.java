package fr.iut.bc.pkdxapi.models.User;

public class UserStatusRequest {
    private String id;
    private boolean isAdmin;

    public UserStatusRequest(String id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
