package com.example.es.sg.auth.db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USERS")
public class BackendUser {
    @Id
    String userId;
    String plaintextPassword; // only in demo land ...
    String role; // for example purposes, define only a single role

    public BackendUser() {
    }

    public BackendUser(String userId, String plaintextPassword, String role) {
        this.userId = userId;
        this.plaintextPassword = plaintextPassword;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaintextPassword() {
        return plaintextPassword;
    }

    public void setPlaintextPassword(String plaintextPassword) {
        this.plaintextPassword = plaintextPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return String.format("User(%s, role=%s)", userId, role);
    }
}
