package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ServicioDTO;
import com.example.demo.service.ServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping
    public ServicioDTO createServicio(@RequestBody ServicioDTO dto) {
        return servicioService.createServicio(dto);
    }

    @GetMapping
    public List<ServicioDTO> getAllServicios() {
        return servicioService.getAllServicios();
    }

    @GetMapping("/{id}")
    public ServicioDTO getServicioById(@PathVariable Long id) {
        return servicioService.getServicioById(id);
    }

    @PutMapping("/{id}")
    public ServicioDTO updateServicio(@PathVariable Long id, @RequestBody ServicioDTO dto) {
        return servicioService.updateServicio(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteServicio(@PathVariable Long id) {
        servicioService.deleteServicio(id);
        return "Servicio eliminado";
    }
}
