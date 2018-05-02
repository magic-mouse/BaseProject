package com.github.dronezcc.riser.gui.model;

import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;


public class ApiUser {

    String username;
    String email;
    boolean active;
    Map<String, String> roles;

    public ApiUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "ApiUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}
