package com.challenge.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name="full_name")
    private String fullName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name="email")
    private String email;


    @NotNull
    @Size(min = 1, max = 50)
    @Column(name="nickname")
    private String nickName;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="created_at")
    private Timestamp createdAt;


    //mapeamento canditado
    @ManyToOne
    @JoinColumn
    private Candidate candidate;


    //mapeamento submission
    @ManyToOne
    @JoinColumn
    private Submission submission;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


}
