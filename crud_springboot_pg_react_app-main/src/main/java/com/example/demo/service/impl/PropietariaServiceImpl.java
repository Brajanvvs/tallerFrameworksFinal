package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PropietariaDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Propietaria;
import com.example.demo.repository.PropietariaRepository;
import com.example.demo.service.PropietariaService;

@Service
public class PropietariaServiceImpl implements PropietariaService {

    private final PropietariaRepository propietariaRepository;

    public PropietariaServiceImpl(PropietariaRepository propietariaRepository) {
        this.propietariaRepository = propietariaRepository;
    }

    @Override
    public PropietariaDTO createPropietaria(PropietariaDTO dto) {
        Propietaria propietaria = new Propietaria();
        propietaria.setName(dto.getName());
        propietaria.setEmail(dto.getEmail());
        propietaria.setPassword(dto.getPassword());

        Propietaria saved = propietariaRepository.save(propietaria);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<PropietariaDTO> getAllPropietarias() {
        return propietariaRepository.findAll().stream().map(propietaria -> {
            PropietariaDTO dto = new PropietariaDTO();
            dto.setId(propietaria.getId());
            dto.setName(propietaria.getName());
            dto.setEmail(propietaria.getEmail());
            dto.setPassword(null);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public PropietariaDTO getPropietariaById(Long id) {
        Propietaria propietaria = propietariaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        PropietariaDTO dto = new PropietariaDTO();
        dto.setId(propietaria.getId());
        dto.setName(propietaria.getName());
        dto.setEmail(propietaria.getEmail());
        return dto;
    }

    @Override
    public PropietariaDTO updatePropietaria(Long id, PropietariaDTO dto) {
        Propietaria propietaria = propietariaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        propietaria.setName(dto.getName());
        propietaria.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            propietaria.setPassword(dto.getPassword());
        }

        Propietaria updated = propietariaRepository.save(propietaria);
        dto.setId(updated.getId());
        return dto;
    }

    @Override
    public void deletePropietaria(Long id) {
        propietariaRepository.deleteById(id);
    }
}
