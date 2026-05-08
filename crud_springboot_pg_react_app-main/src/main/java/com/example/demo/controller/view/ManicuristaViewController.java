package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.ManicuristaDTO;
import com.example.demo.repository.PropietariaRepository;
import com.example.demo.service.ManicuristaService;

@Controller
@RequestMapping("/view/manicuristas")
public class ManicuristaViewController {

    private final ManicuristaService manicuristaService;
    private final PropietariaRepository propietariaRepository;

    public ManicuristaViewController(ManicuristaService manicuristaService,
                                      PropietariaRepository propietariaRepository) {
        this.manicuristaService = manicuristaService;
        this.propietariaRepository = propietariaRepository;
    }

    @GetMapping
    public String listarManicuristas(Model model) {
        model.addAttribute("manicuristas", manicuristaService.getAllManicuristas());
        model.addAttribute("manicurista", new ManicuristaDTO());
        model.addAttribute("propietarias", propietariaRepository.findAll());
        return "Manicurista";
    }

    @PostMapping("/guardar")
    public String guardarManicurista(@Valid @ModelAttribute("manicurista") ManicuristaDTO dto,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manicuristas", manicuristaService.getAllManicuristas());
            model.addAttribute("propietarias", propietariaRepository.findAll());
            return "Manicurista";
        }
        if (dto.getId() == null) {
            manicuristaService.createManicurista(dto);
        } else {
            manicuristaService.updateManicurista(dto.getId(), dto);
        }
        return "redirect:/view/manicuristas";
    }

    @GetMapping("/editar/{id}")
    public String editarManicurista(@PathVariable Long id, Model model) {
        ManicuristaDTO dto = manicuristaService.getManicuristaById(id);
        model.addAttribute("manicurista", dto);
        model.addAttribute("manicuristas", manicuristaService.getAllManicuristas());
        model.addAttribute("propietarias", propietariaRepository.findAll());
        return "Manicurista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarManicurista(@PathVariable Long id) {
        manicuristaService.deleteManicurista(id);
        return "redirect:/view/manicuristas";
    }
}
