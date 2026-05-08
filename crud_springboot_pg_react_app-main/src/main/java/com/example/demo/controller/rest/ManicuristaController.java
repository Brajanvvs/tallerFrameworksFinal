package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ManicuristaDTO;
import com.example.demo.service.ManicuristaService;

@RestController
@RequestMapping("/api/manicuristas")
public class ManicuristaController {

    private final ManicuristaService manicuristaService;

    public ManicuristaController(ManicuristaService manicuristaService) {
        this.manicuristaService = manicuristaService;
    }

    @PostMapping
    public ManicuristaDTO createManicurista(@RequestBody ManicuristaDTO dto) {
        return manicuristaService.createManicurista(dto);
    }

    @GetMapping
    public List<ManicuristaDTO> getAllManicuristas() {
        return manicuristaService.getAllManicuristas();
    }

    @GetMapping("/{id}")
    public ManicuristaDTO getManicuristaById(@PathVariable Long id) {
        return manicuristaService.getManicuristaById(id);
    }

    @PutMapping("/{id}")
    public ManicuristaDTO updateManicurista(@PathVariable Long id, @RequestBody ManicuristaDTO dto) {
        return manicuristaService.updateManicurista(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteManicurista(@PathVariable Long id) {
        manicuristaService.deleteManicurista(id);
        return "Manicurista eliminado";
    }
}
