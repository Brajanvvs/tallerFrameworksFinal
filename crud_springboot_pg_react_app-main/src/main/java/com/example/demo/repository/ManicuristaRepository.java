package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Manicurista;

public interface ManicuristaRepository extends JpaRepository<Manicurista, Long> {
}