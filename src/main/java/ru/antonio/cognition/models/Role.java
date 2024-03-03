package ru.antonio.cognition.models;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Simple JavaBean Object that represents role of {@link User}
 * @author Antonio Panotti
 * @version 1.0
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    private String role;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
