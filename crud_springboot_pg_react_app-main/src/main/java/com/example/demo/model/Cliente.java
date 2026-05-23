package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cli")
    private Long id;

    private String name;
    private String email;
    private String cellphone;
    private String password;

    @ManyToOne
    @JoinColumn(name = "sadmin_id")
    private SuperAdministrador superAdministrador;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SuperAdministrador getSuperAdministrador() {
        return superAdministrador;
    }

    public void setSuperAdministrador(SuperAdministrador superAdministrador) {
        this.superAdministrador = superAdministrador;
    }
}