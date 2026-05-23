package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final ClienteService clienteService;

    public RegistroController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String formularioRegistro(Model model) {
        model.addAttribute("cliente", new ClienteDTO());
        return "Registro";
    }

    @PostMapping
    public String registrarCliente(@Valid @ModelAttribute("cliente") ClienteDTO dto,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Registro";
        }
        clienteService.createCliente(dto);
        return "redirect:/view/login?registro=exitoso";
    }
}
