package com.challenge.entity;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity(name="candidate")
public class Candidate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull
    @Column(name="status")
    private int status;

    @NotNull
    @Column(name="created_at")
    private Timestamp createdAt;


    //mapeamento user
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<User> user;

    // mapeamento acelaration
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Aceleration> acelerations;

    // mapeamento company
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Company> companies;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Aceleration> getAcelerations() {
        return acelerations;
    }

    public void setAcelerations(List<Aceleration> acelerations) {
        this.acelerations = acelerations;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
