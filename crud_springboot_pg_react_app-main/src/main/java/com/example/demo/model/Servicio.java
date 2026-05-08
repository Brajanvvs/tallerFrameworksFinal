package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ser")
    private Long id;

    @Column(name = "name_service")
    private String nameService;

    private String description;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "mani_id")
    private Manicurista manicurista;

    public Servicio() {}

    public Long getId() {
        return id;
    }

    public String getNameService() {
        return nameService;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Manicurista getManicurista() {
        return manicurista;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setManicurista(Manicurista manicurista) {
        this.manicurista = manicurista;
    }
}