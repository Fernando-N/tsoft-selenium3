package dev.fneira.business;

public class LoginData {

    private String username;
    private String password;

    public LoginData() {
    }

    public String getUsername() {
        return username;
    }

    public LoginData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginData setPassword(String password) {
        this.password = password;
        return this;
    }

}
