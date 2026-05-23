package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Propietaria;
import com.example.demo.model.SuperAdministrador;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PropietariaRepository;
import com.example.demo.repository.SuperAdministradorRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final ClienteRepository clienteRepository;
    private final PropietariaRepository propietariaRepository;
    private final SuperAdministradorRepository superAdministradorRepository;

    public AuthServiceImpl(ClienteRepository clienteRepository,
                           PropietariaRepository propietariaRepository,
                           SuperAdministradorRepository superAdministradorRepository) {
        this.clienteRepository = clienteRepository;
        this.propietariaRepository = propietariaRepository;
        this.superAdministradorRepository = superAdministradorRepository;
    }

    @Override
    public String authenticate(String email, String password) {
        if (clienteRepository.findAll().stream()
                .anyMatch(c -> c.getEmail().equals(email) && c.getPassword().equals(password))) {
            return "CLIENTE";
        }
        if (propietariaRepository.findAll().stream()
                .anyMatch(p -> p.getEmail().equals(email) && p.getPassword().equals(password))) {
            return "ADMIN";
        }
        if (superAdministradorRepository.findAll().stream()
                .anyMatch(s -> s.getEmail().equals(email) && s.getPassword().equals(password))) {
            return "ADMIN";
        }
        return null;
    }

    @Override
    public String getUserName(String email) {
        return clienteRepository.findByEmail(email)
                .map(Cliente::getName)
                .orElseGet(() -> propietariaRepository.findAll().stream()
                        .filter(p -> p.getEmail().equals(email))
                        .findFirst()
                        .map(Propietaria::getName)
                        .orElseGet(() -> superAdministradorRepository.findAll().stream()
                                .filter(s -> s.getEmail().equals(email))
                                .findFirst()
                                .map(SuperAdministrador::getName)
                                .orElse(null)));
    }
}
