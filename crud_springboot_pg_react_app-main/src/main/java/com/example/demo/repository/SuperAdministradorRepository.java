package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SuperAdministrador;

public interface SuperAdministradorRepository extends JpaRepository<SuperAdministrador, Long> {
}
