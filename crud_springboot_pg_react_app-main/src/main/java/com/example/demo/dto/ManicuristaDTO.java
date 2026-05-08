package com.example.demo.dto;

public class ManicuristaDTO {

    private Long id;
    private String name;
    private String specialization;
    private String services;
    private Long propietariaId;

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

    public Long getPropietariaId() {
        return propietariaId;
    }

    public void setPropietariaId(Long propietariaId) {
        this.propietariaId = propietariaId;
    }
}