package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SuperAdministradorDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.SuperAdministrador;
import com.example.demo.repository.SuperAdministradorRepository;
import com.example.demo.service.SuperAdministradorService;

@Service
public class SuperAdministradorServiceImpl implements SuperAdministradorService {

    private final SuperAdministradorRepository repository;

    public SuperAdministradorServiceImpl(SuperAdministradorRepository repository) {
        this.repository = repository;
    }

    @Override
    public SuperAdministradorDTO createSuperAdministrador(SuperAdministradorDTO dto) {
        SuperAdministrador entity = new SuperAdministrador();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        SuperAdministrador saved = repository.save(entity);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<SuperAdministradorDTO> getAllSuperAdministradores() {
        return repository.findAll().stream().map(entity -> {
            SuperAdministradorDTO dto = new SuperAdministradorDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SuperAdministradorDTO getSuperAdministradorById(Long id) {
        SuperAdministrador entity = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        SuperAdministradorDTO dto = new SuperAdministradorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    @Override
    public SuperAdministradorDTO updateSuperAdministrador(Long id, SuperAdministradorDTO dto) {
        SuperAdministrador entity = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            entity.setPassword(dto.getPassword());
        }

        SuperAdministrador updated = repository.save(entity);
        dto.setId(updated.getId());
        return dto;
    }

    @Override
    public void deleteSuperAdministrador(Long id) {
        repository.deleteById(id);
    }
}
