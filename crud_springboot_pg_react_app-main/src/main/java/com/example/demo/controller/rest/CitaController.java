package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CitaDTO;
import com.example.demo.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public CitaDTO createCita(@RequestBody CitaDTO citaDTO) {
        return citaService.createCita(citaDTO);
    }

    @GetMapping
    public List<CitaDTO> getAllCitas() {
        return citaService.getAllCitas();
    }

    @GetMapping("/{id}")
    public CitaDTO getCitaById(@PathVariable Long id) {
        return citaService.getCitaById(id);
    }

    @PutMapping("/{id}")
    public CitaDTO updateCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        return citaService.updateCita(id, citaDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return "Cita eliminada";
    }
}
