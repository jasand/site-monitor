package no.sensor.service.model.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class AuthResponse {
    private String userName;
    private List<String> roles;
    private String token;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date expires;

    public AuthResponse(String userName, List<String> roles, String token, Date expires) {
        this.userName = userName;
        this.roles = roles;
        this.token = token;
        this.expires = expires;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
