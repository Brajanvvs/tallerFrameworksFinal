package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ManicuristaDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Manicurista;
import com.example.demo.model.Propietaria;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.repository.PropietariaRepository;
import com.example.demo.service.ManicuristaService;

@Service
public class ManicuristaServiceImpl implements ManicuristaService {

    private final ManicuristaRepository manicuristaRepository;
    private final PropietariaRepository propietariaRepository;

    public ManicuristaServiceImpl(ManicuristaRepository manicuristaRepository,
                                   PropietariaRepository propietariaRepository) {
        this.manicuristaRepository = manicuristaRepository;
        this.propietariaRepository = propietariaRepository;
    }

    @Override
    public ManicuristaDTO createManicurista(ManicuristaDTO dto) {
        Manicurista manicurista = new Manicurista();
        manicurista.setName(dto.getName());
        manicurista.setSpecialization(dto.getSpecialization());
        manicurista.setServices(dto.getServices());

        Propietaria propietaria = propietariaRepository.findById(dto.getPropietariaId())
                .orElseThrow(() -> new UserNotFoundException(dto.getPropietariaId()));
        manicurista.setPropietaria(propietaria);

        Manicurista saved = manicuristaRepository.save(manicurista);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<ManicuristaDTO> getAllManicuristas() {
        return manicuristaRepository.findAll().stream().map(manicurista -> {
            ManicuristaDTO dto = new ManicuristaDTO();
            dto.setId(manicurista.getId());
            dto.setName(manicurista.getName());
            dto.setSpecialization(manicurista.getSpecialization());
            dto.setServices(manicurista.getServices());
            dto.setPropietariaId(manicurista.getPropietaria().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ManicuristaDTO getManicuristaById(Long id) {
        Manicurista manicurista = manicuristaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        ManicuristaDTO dto = new ManicuristaDTO();
        dto.setId(manicurista.getId());
        dto.setName(manicurista.getName());
        dto.setSpecialization(manicurista.getSpecialization());
        dto.setServices(manicurista.getServices());
        dto.setPropietariaId(manicurista.getPropietaria().getId());
        return dto;
    }

    @Override
    public ManicuristaDTO updateManicurista(Long id, ManicuristaDTO dto) {
        Manicurista manicurista = manicuristaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        manicurista.setName(dto.getName());
        manicurista.setSpecialization(dto.getSpecialization());
        manicurista.setServices(dto.getServices());

        if (dto.getPropietariaId() != null) {
            Propietaria propietaria = propietariaRepository.findById(dto.getPropietariaId())
                    .orElseThrow(() -> new UserNotFoundException(dto.getPropietariaId()));
            manicurista.setPropietaria(propietaria);
        }

        Manicurista updated = manicuristaRepository.save(manicurista);
        dto.setId(updated.getId());
        return dto;
    }

    @Override
    public void deleteManicurista(Long id) {
        manicuristaRepository.deleteById(id);
    }
}
