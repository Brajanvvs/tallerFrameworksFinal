package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.CitaDTO;

public interface CitaService {

    CitaDTO createCita(CitaDTO citaDTO);

    List<CitaDTO> getAllCitas();

    CitaDTO getCitaById(Long id);

    CitaDTO updateCita(Long id, CitaDTO citaDTO);

    void deleteCita(Long id);
}
