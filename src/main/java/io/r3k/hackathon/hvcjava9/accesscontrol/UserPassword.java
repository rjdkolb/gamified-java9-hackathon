package io.r3k.hackathon.hvcjava9.accesscontrol;

public class UserPassword {

    private String user;
    private String password;

    public UserPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public UserPassword() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPassword{" + "user=" + user + ", password=" + password + '}';
    }
    
    
    
}
