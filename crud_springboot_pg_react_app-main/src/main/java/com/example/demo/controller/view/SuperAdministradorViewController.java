package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.SuperAdministradorDTO;
import com.example.demo.service.SuperAdministradorService;

@Controller
@RequestMapping("/view/super-administradores")
public class SuperAdministradorViewController {

    private final SuperAdministradorService service;

    public SuperAdministradorViewController(SuperAdministradorService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("superAdministradores", service.getAllSuperAdministradores());
        model.addAttribute("superAdministrador", new SuperAdministradorDTO());
        return "SuperAdministrador";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("superAdministrador") SuperAdministradorDTO dto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("superAdministradores", service.getAllSuperAdministradores());
            return "SuperAdministrador";
        }
        if (dto.getId() == null) {
            service.createSuperAdministrador(dto);
        } else {
            service.updateSuperAdministrador(dto.getId(), dto);
        }
        return "redirect:/view/super-administradores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("superAdministrador", service.getSuperAdministradorById(id));
        model.addAttribute("superAdministradores", service.getAllSuperAdministradores());
        return "SuperAdministrador";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.deleteSuperAdministrador(id);
        return "redirect:/view/super-administradores";
    }
}
