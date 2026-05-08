package com.example.demo.controller.rest;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ClienteDTO createCliente(@Valid @RequestBody ClienteDTO cliente) {
        return clienteService.createCliente(cliente);
    }

    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/api/{id}")
    public ClienteDTO getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PutMapping("/api/{id}")
    public ClienteDTO updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/api/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "Cliente eliminado";
    }
}