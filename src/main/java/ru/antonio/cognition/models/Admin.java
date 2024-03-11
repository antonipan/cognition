package ru.antonio.cognition.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User{

    private String name;
    private String status;

    public Admin(String username, String password, Role role, String name, String status) {
        super(username, password, role);
        this.name = name;
        this.status = status;
    }

    public Admin() {

    }

    public Admin(String username, String password, Role role) {
        super(username, password, role);
        this.name = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
