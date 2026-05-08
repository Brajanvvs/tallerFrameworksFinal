package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Propietaria;

public interface PropietariaRepository extends JpaRepository<Propietaria, Long> {
}