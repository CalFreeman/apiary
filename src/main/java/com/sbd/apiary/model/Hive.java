package com.sbd.apiary.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hives")
public class Hive extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "apiary_id", referencedColumnName = "id")
    private Apiary apiary;

    // @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apiary", cascade = CascadeType.ALL)
    private List<Hive> hives = new ArrayList<>();

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Apiary getApiary()
    {
        return apiary;
    }

    public void setApiary(Apiary apiary)
    {
        this.apiary = apiary;
    }

    public List<Hive> getHives() {
        return hives;
    }

    public void setHives(List<Hive> hives) {
        this.hives = hives;
    }
}