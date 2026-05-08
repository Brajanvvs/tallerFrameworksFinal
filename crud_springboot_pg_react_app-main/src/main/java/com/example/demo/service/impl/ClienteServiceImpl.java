package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setName(dto.getName());
        cliente.setEmail(dto.getEmail());
        cliente.setCellphone(dto.getCellphone());

        Cliente saved = clienteRepository.save(cliente);

        dto.setId(saved.getId());

        return dto;
    }

    @Override
    public List<ClienteDTO> getAllClientes() {

        return clienteRepository.findAll().stream().map(cliente -> {

            ClienteDTO dto = new ClienteDTO();

            dto.setId(cliente.getId());
            dto.setName(cliente.getName());
            dto.setEmail(cliente.getEmail());
            dto.setCellphone(cliente.getCellphone());

            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getClienteById(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        ClienteDTO dto = new ClienteDTO();

        dto.setId(cliente.getId());
        dto.setName(cliente.getName());
        dto.setEmail(cliente.getEmail());
        dto.setCellphone(cliente.getCellphone());

        return dto;
    }

    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        cliente.setName(dto.getName());
        cliente.setEmail(dto.getEmail());
        cliente.setCellphone(dto.getCellphone());

        Cliente updated = clienteRepository.save(cliente);

        dto.setId(updated.getId());
        return dto;
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}