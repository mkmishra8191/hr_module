package tech.getarrays.employeemanager.model;

public class LoginResponse {

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    private final String token;
    private final  String type;


    private final String message;


    public LoginResponse(String token, String type, String message) {
        this.token = token;
        this.type = type;
        this.message = message;
    }




}
