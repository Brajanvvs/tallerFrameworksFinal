package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class CitaDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateHour;

    private String state;
    private Long clienteId;
    private Long manicuristaId;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(LocalDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getManicuristaId() {
        return manicuristaId;
    }

    public void setManicuristaId(Long manicuristaId) {
        this.manicuristaId = manicuristaId;
    }
}