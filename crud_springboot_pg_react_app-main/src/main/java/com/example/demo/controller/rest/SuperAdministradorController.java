package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SuperAdministradorDTO;
import com.example.demo.service.SuperAdministradorService;

@RestController
@RequestMapping("/api/super-administradores")
public class SuperAdministradorController {

    private final SuperAdministradorService service;

    public SuperAdministradorController(SuperAdministradorService service) {
        this.service = service;
    }

    @PostMapping
    public SuperAdministradorDTO create(@RequestBody SuperAdministradorDTO dto) {
        return service.createSuperAdministrador(dto);
    }

    @GetMapping
    public List<SuperAdministradorDTO> getAll() {
        return service.getAllSuperAdministradores();
    }

    @GetMapping("/{id}")
    public SuperAdministradorDTO getById(@PathVariable Long id) {
        return service.getSuperAdministradorById(id);
    }

    @PutMapping("/{id}")
    public SuperAdministradorDTO update(@PathVariable Long id, @RequestBody SuperAdministradorDTO dto) {
        return service.updateSuperAdministrador(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteSuperAdministrador(id);
        return "Super administrador eliminado";
    }
}
