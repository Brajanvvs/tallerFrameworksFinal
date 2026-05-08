package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CitaDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Cita;
import com.example.demo.model.Cliente;
import com.example.demo.model.Manicurista;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final ManicuristaRepository manicuristaRepository;

    public CitaServiceImpl(CitaRepository citaRepository,
                            ClienteRepository clienteRepository,
                            ManicuristaRepository manicuristaRepository) {
        this.citaRepository = citaRepository;
        this.clienteRepository = clienteRepository;
        this.manicuristaRepository = manicuristaRepository;
    }

    @Override
    public CitaDTO createCita(CitaDTO dto) {
        Cita cita = new Cita();
        cita.setDateHour(dto.getDateHour());
        cita.setState(dto.getState());

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new UserNotFoundException(dto.getClienteId()));
        Manicurista manicurista = manicuristaRepository.findById(dto.getManicuristaId())
                .orElseThrow(() -> new UserNotFoundException(dto.getManicuristaId()));

        cita.setCliente(cliente);
        cita.setManicurista(manicurista);

        Cita saved = citaRepository.save(cita);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<CitaDTO> getAllCitas() {
        return citaRepository.findAll().stream().map(cita -> {
            CitaDTO dto = new CitaDTO();
            dto.setId(cita.getId());
            dto.setDateHour(cita.getDateHour());
            dto.setState(cita.getState());
            dto.setClienteId(cita.getCliente().getId());
            dto.setManicuristaId(cita.getManicurista().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CitaDTO getCitaById(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setDateHour(cita.getDateHour());
        dto.setState(cita.getState());
        dto.setClienteId(cita.getCliente().getId());
        dto.setManicuristaId(cita.getManicurista().getId());
        return dto;
    }

    @Override
    public CitaDTO updateCita(Long id, CitaDTO dto) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        cita.setDateHour(dto.getDateHour());
        cita.setState(dto.getState());

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new UserNotFoundException(dto.getClienteId()));
            cita.setCliente(cliente);
        }

        if (dto.getManicuristaId() != null) {
            Manicurista manicurista = manicuristaRepository.findById(dto.getManicuristaId())
                    .orElseThrow(() -> new UserNotFoundException(dto.getManicuristaId()));
            cita.setManicurista(manicurista);
        }

        Cita updated = citaRepository.save(cita);
        dto.setId(updated.getId());
        return dto;
    }

    @Override
    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }
}
