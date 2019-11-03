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


@Entity(name="challenge")
public class Challenge {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @NotNull
    @Size(min = 1, max = 100)
    @Column(name="name")
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name="slug")
    private String slug;


    @NotNull
    @Column(name="created_at")
    private Timestamp createdAt;


    //mapeamento submission
    @ManyToOne
    @JoinColumn
    private Submission submission;


    //mapeamento aceleration
    @ManyToOne
    @JoinColumn
    private Aceleration aceleration;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
