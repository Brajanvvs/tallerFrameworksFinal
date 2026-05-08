package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ServicioDTO;

public interface ServicioService {

    ServicioDTO createServicio(ServicioDTO dto);

    List<ServicioDTO> getAllServicios();

    ServicioDTO getServicioById(Long id);

    ServicioDTO updateServicio(Long id, ServicioDTO dto);

    void deleteServicio(Long id);
}
