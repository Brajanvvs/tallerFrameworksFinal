package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ServicioDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Manicurista;
import com.example.demo.model.Servicio;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final ManicuristaRepository manicuristaRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository,
                                ManicuristaRepository manicuristaRepository) {
        this.servicioRepository = servicioRepository;
        this.manicuristaRepository = manicuristaRepository;
    }

    @Override
    public ServicioDTO createServicio(ServicioDTO dto) {
        Servicio entity = new Servicio();
        entity.setNameService(dto.getNameService());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        Manicurista manicurista = manicuristaRepository.findById(dto.getManicuristaId())
                .orElseThrow(() -> new UserNotFoundException(dto.getManicuristaId()));
        entity.setManicurista(manicurista);

        Servicio saved = servicioRepository.save(entity);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<ServicioDTO> getAllServicios() {
        return servicioRepository.findAll().stream().map(entity -> {
            ServicioDTO dto = new ServicioDTO();
            dto.setId(entity.getId());
            dto.setNameService(entity.getNameService());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setManicuristaId(entity.getManicurista().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ServicioDTO getServicioById(Long id) {
        Servicio entity = servicioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        ServicioDTO dto = new ServicioDTO();
        dto.setId(entity.getId());
        dto.setNameService(entity.getNameService());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setManicuristaId(entity.getManicurista().getId());
        return dto;
    }

    @Override
    public ServicioDTO updateServicio(Long id, ServicioDTO dto) {
        Servicio entity = servicioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        entity.setNameService(dto.getNameService());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        if (dto.getManicuristaId() != null) {
            Manicurista manicurista = manicuristaRepository.findById(dto.getManicuristaId())
                    .orElseThrow(() -> new UserNotFoundException(dto.getManicuristaId()));
            entity.setManicurista(manicurista);
        }

        Servicio updated = servicioRepository.save(entity);
        dto.setId(updated.getId());
        return dto;
    }

    @Override
    public void deleteServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}
