package tech.getarrays.employeemanager.model;


public class AuthRequest {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;

    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AuthRequest() {
    }
}