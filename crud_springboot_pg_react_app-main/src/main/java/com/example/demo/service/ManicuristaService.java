package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.ManicuristaDTO;

public interface ManicuristaService {

    ManicuristaDTO createManicurista(ManicuristaDTO dto);

    List<ManicuristaDTO> getAllManicuristas();

    ManicuristaDTO getManicuristaById(Long id);

    ManicuristaDTO updateManicurista(Long id, ManicuristaDTO dto);

    void deleteManicurista(Long id);
}
