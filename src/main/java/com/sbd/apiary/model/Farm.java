package com.sbd.apiary.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "farms")
public class Farm extends AuditModel {
    @Id
    @GeneratedValue(generator = "farm_generator")
    @SequenceGenerator(
            name = "farm_generator",
            sequenceName = "farm_sequence",
            initialValue = 1000
    )
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "farm_id")
    @OrderBy("name")
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
}