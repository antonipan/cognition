package ru.antonio.cognition.models;

import jakarta.persistence.*;


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
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
