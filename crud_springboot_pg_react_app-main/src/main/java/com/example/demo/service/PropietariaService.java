package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.PropietariaDTO;

public interface PropietariaService {

    PropietariaDTO createPropietaria(PropietariaDTO dto);

    List<PropietariaDTO> getAllPropietarias();

    PropietariaDTO getPropietariaById(Long id);

    PropietariaDTO updatePropietaria(Long id, PropietariaDTO dto);

    void deletePropietaria(Long id);
}
