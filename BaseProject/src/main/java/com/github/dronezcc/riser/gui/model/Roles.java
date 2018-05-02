package com.github.dronezcc.riser.gui.model;

public class Roles {

    String role_admin;
    String role_user;

    public Roles() {
    }

    public String getRole_admin() {
        return role_admin;
    }

    public void setRole_admin(String role_admin) {
        this.role_admin = role_admin;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role_admin='" + role_admin + '\'' +
                ", role_user='" + role_user + '\'' +
                '}';
    }
}
