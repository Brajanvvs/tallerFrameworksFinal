package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.SuperAdministradorDTO;

public interface SuperAdministradorService {

    SuperAdministradorDTO createSuperAdministrador(SuperAdministradorDTO dto);

    List<SuperAdministradorDTO> getAllSuperAdministradores();

    SuperAdministradorDTO getSuperAdministradorById(Long id);

    SuperAdministradorDTO updateSuperAdministrador(Long id, SuperAdministradorDTO dto);

    void deleteSuperAdministrador(Long id);
}
