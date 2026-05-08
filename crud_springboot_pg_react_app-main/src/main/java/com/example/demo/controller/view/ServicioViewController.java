package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.ServicioDTO;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.service.ServicioService;

@Controller
@RequestMapping("/view/servicios")
public class ServicioViewController {

    private final ServicioService servicioService;
    private final ManicuristaRepository manicuristaRepository;

    public ServicioViewController(ServicioService servicioService,
                                   ManicuristaRepository manicuristaRepository) {
        this.servicioService = servicioService;
        this.manicuristaRepository = manicuristaRepository;
    }

    @GetMapping
    public String listarServicios(Model model) {
        model.addAttribute("servicios", servicioService.getAllServicios());
        model.addAttribute("servicio", new ServicioDTO());
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        return "Servicio";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@Valid @ModelAttribute("servicio") ServicioDTO dto,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("servicios", servicioService.getAllServicios());
            model.addAttribute("manicuristas", manicuristaRepository.findAll());
            return "Servicio";
        }
        if (dto.getId() == null) {
            servicioService.createServicio(dto);
        } else {
            servicioService.updateServicio(dto.getId(), dto);
        }
        return "redirect:/view/servicios";
    }

    @GetMapping("/editar/{id}")
    public String editarServicio(@PathVariable Long id, Model model) {
        model.addAttribute("servicio", servicioService.getServicioById(id));
        model.addAttribute("servicios", servicioService.getAllServicios());
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        return "Servicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id) {
        servicioService.deleteServicio(id);
        return "redirect:/view/servicios";
    }
}
