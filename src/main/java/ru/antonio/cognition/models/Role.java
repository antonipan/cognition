package ru.antonio.cognition.models;

import jakarta.persistence.*;


/**
 * Класс сущности "Роль" {@link Role}
 *
 * @author Antonio
 * @version 1.0
 */
@Entity
@Table(name = "roles")
public class Role {
// TODO: 16.03.2024 дописать документацию

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

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
