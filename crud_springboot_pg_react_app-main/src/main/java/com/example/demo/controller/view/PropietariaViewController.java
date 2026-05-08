package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.PropietariaDTO;
import com.example.demo.service.PropietariaService;

@Controller
@RequestMapping("/view/propietarias")
public class PropietariaViewController {

    private final PropietariaService propietariaService;

    public PropietariaViewController(PropietariaService propietariaService) {
        this.propietariaService = propietariaService;
    }

    @GetMapping
    public String listarPropietarias(Model model) {
        model.addAttribute("propietarias", propietariaService.getAllPropietarias());
        model.addAttribute("propietaria", new PropietariaDTO());
        return "Propietaria";
    }

    @PostMapping("/guardar")
    public String guardarPropietaria(@Valid @ModelAttribute("propietaria") PropietariaDTO dto,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("propietarias", propietariaService.getAllPropietarias());
            return "Propietaria";
        }
        if (dto.getId() == null) {
            propietariaService.createPropietaria(dto);
        } else {
            propietariaService.updatePropietaria(dto.getId(), dto);
        }
        return "redirect:/view/propietarias";
    }

    @GetMapping("/editar/{id}")
    public String editarPropietaria(@PathVariable Long id, Model model) {
        PropietariaDTO dto = propietariaService.getPropietariaById(id);
        model.addAttribute("propietaria", dto);
        model.addAttribute("propietarias", propietariaService.getAllPropietarias());
        return "Propietaria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPropietaria(@PathVariable Long id) {
        propietariaService.deletePropietaria(id);
        return "redirect:/view/propietarias";
    }
}
