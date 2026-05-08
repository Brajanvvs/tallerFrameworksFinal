package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/view/clientes")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("cliente", new ClienteDTO());
        return "Cliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("cliente") ClienteDTO cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.getAllClientes());
            return "Cliente";
        }
        if (cliente.getId() == null) {
            clienteService.createCliente(cliente);
        } else {
            clienteService.updateCliente(cliente.getId(), cliente);
        }
        return "redirect:/view/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        ClienteDTO cliente = clienteService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "Cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/view/clientes";
    }
}
