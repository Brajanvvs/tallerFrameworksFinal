package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO createCliente(ClienteDTO cliente);

    List<ClienteDTO> getAllClientes();

    ClienteDTO getClienteById(Long id);

    ClienteDTO updateCliente(Long id, ClienteDTO cliente);

    void deleteCliente(Long id);
}