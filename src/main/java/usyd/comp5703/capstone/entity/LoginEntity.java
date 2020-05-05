package usyd.comp5703.capstone.entity;

public class LoginEntity {
    String id;
    String password;

    public LoginEntity(String username, String password) {
        this.id = username;
        this.password = password;
    }

    public void setId(String username) {
        this.id = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
