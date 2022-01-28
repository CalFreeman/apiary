package com.sbd.apiary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apiarys")
public class Apiary extends AuditModel {
    @Id
    @GeneratedValue(generator = "apiary_generator")
    @SequenceGenerator(
            name = "apiary_generator",
            sequenceName = "apiary_sequence",
            initialValue = 1000
    )
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "farm_id", referencedColumnName  = "id")
    private Farm farm;

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
    public Farm getFarm()
    {
        return farm;
    }
    public void setFarm(Farm farm)
    {
        this.farm = farm;
    }
}