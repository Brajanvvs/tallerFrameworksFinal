package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/view/clientes")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    private String checkSession(HttpSession session) {
        return (session.getAttribute("userName") == null) ? "redirect:/view/login" : null;
    }

    @GetMapping
    public String listarClientes(Model model, HttpSession session) {
        String redirect = checkSession(session);
        if (redirect != null) return redirect;
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("cliente", new ClienteDTO());
        return "Cliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("cliente") ClienteDTO cliente, BindingResult result, Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
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
    public String editarCliente(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
        model.addAttribute("cliente", clienteService.getClienteById(id));
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "Cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
        clienteService.deleteCliente(id);
        return "redirect:/view/clientes";
    }
}
