package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.CitaDTO;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.service.CitaService;

@Controller
@RequestMapping("/view/citas")
public class CitaViewController {

    private final CitaService citaService;
    private final ClienteRepository clienteRepository;
    private final ManicuristaRepository manicuristaRepository;

    public CitaViewController(CitaService citaService,
                              ClienteRepository clienteRepository,
                              ManicuristaRepository manicuristaRepository) {
        this.citaService = citaService;
        this.clienteRepository = clienteRepository;
        this.manicuristaRepository = manicuristaRepository;
    }

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.getAllCitas());
        model.addAttribute("cita", new CitaDTO());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        return "Cita";
    }

    @PostMapping("/guardar")
    public String guardarCita(@Valid @ModelAttribute("cita") CitaDTO citaDTO,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("citas", citaService.getAllCitas());
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("manicuristas", manicuristaRepository.findAll());
            return "Cita";
        }
        if (citaDTO.getId() == null) {
            citaService.createCita(citaDTO);
        } else {
            citaService.updateCita(citaDTO.getId(), citaDTO);
        }
        return "redirect:/view/citas";
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        CitaDTO cita = citaService.getCitaById(id);
        model.addAttribute("cita", cita);
        model.addAttribute("citas", citaService.getAllCitas());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        return "Cita";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return "redirect:/view/citas";
    }
}
