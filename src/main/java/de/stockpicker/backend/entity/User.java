package de.stockpicker.backend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Entität zur Speicherung der Benutzer der Applikation
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column
    private String role;

    @Column
    private boolean active;

    @Column
    private double entryFee;
}
