package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ci")
    private Long id;

    @Column(name = "date_hour")
    private LocalDateTime dateHour;

    private String state;

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mani_id")
    private Manicurista manicurista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(LocalDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Manicurista getManicurista() {
        return manicurista;
    }

    public void setManicurista(Manicurista manicurista) {
        this.manicurista = manicurista;
    }
}