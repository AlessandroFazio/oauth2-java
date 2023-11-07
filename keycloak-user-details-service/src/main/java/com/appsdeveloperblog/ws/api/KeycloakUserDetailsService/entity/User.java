package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastname;

    public String getFirstName() {
        return firstName;
    }

    public User(String firstName, String lastname, String email, String password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String firstName, String lastname, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
