package com.example.demo.dto;

public class ServicioDTO {

    private Long id;
    private String nameService;
    private String description;
    private Double price;
    private Long manicuristaId;

    public Long getId() {
        return id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getManicuristaId() {
        return manicuristaId;
    }

    public void setManicuristaId(Long manicuristaId) {
        this.manicuristaId = manicuristaId;
    }
}