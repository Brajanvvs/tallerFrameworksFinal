package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manicurista")
public class Manicurista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mani")
    private Long id;

    private String name;
    private String specialization;

    private String services;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Propietaria propietaria;

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Propietaria getPropietaria() {
        return propietaria;
    }

    public void setPropietaria(Propietaria propietaria) {
        this.propietaria = propietaria;
    }
}