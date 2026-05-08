package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PropietariaDTO;
import com.example.demo.service.PropietariaService;

@RestController
@RequestMapping("/api/propietarias")
public class PropietariaController {

    private final PropietariaService propietariaService;

    public PropietariaController(PropietariaService propietariaService) {
        this.propietariaService = propietariaService;
    }

    @PostMapping
    public PropietariaDTO createPropietaria(@RequestBody PropietariaDTO dto) {
        return propietariaService.createPropietaria(dto);
    }

    @GetMapping
    public List<PropietariaDTO> getAllPropietarias() {
        return propietariaService.getAllPropietarias();
    }

    @GetMapping("/{id}")
    public PropietariaDTO getPropietariaById(@PathVariable Long id) {
        return propietariaService.getPropietariaById(id);
    }

    @PutMapping("/{id}")
    public PropietariaDTO updatePropietaria(@PathVariable Long id, @RequestBody PropietariaDTO dto) {
        return propietariaService.updatePropietaria(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deletePropietaria(@PathVariable Long id) {
        propietariaService.deletePropietaria(id);
        return "Propietaria eliminada";
    }
}
