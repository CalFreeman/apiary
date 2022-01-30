package com.sbd.apiary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "farms")
public class Farm extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Apiary> apiarys = new ArrayList<>();

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

    public List<Apiary> getApiarys() {
        return apiarys;
    }

    public void setApiarys(List<Apiary> apiarys) {
        this.apiarys = apiarys;
    }
}