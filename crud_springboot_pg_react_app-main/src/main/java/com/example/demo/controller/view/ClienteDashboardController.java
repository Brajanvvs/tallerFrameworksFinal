package com.example.demo.controller.view;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CitaDTO;
import com.example.demo.model.Cita;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.service.CitaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ClienteDashboardController {

    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final ManicuristaRepository manicuristaRepository;
    private final CitaService citaService;

    public ClienteDashboardController(CitaRepository citaRepository,
                                       ClienteRepository clienteRepository,
                                       ManicuristaRepository manicuristaRepository,
                                       CitaService citaService) {
        this.citaRepository = citaRepository;
        this.clienteRepository = clienteRepository;
        this.manicuristaRepository = manicuristaRepository;
        this.citaService = citaService;
    }

    @GetMapping("/mis-citas")
    public String misCitas(Model model, HttpSession session) {
        if (session.getAttribute("userEmail") == null) return "redirect:/view/login";

        String email = (String) session.getAttribute("userEmail");

        List<Cita> citas = citaRepository.findAll().stream()
                .filter(c -> c.getCliente().getEmail().equals(email))
                .collect(Collectors.toList());

        model.addAttribute("citas", citas);
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        model.addAttribute("nuevaCita", new CitaDTO());
        return "ClienteDashboard";
    }

    @PostMapping("/mis-citas/agendar")
    public String agendarCita(@ModelAttribute CitaDTO dto, HttpSession session) {
        if (session.getAttribute("userEmail") == null) return "redirect:/view/login";

        String email = (String) session.getAttribute("userEmail");
        Long clienteId = clienteRepository.findByEmail(email).get().getId();
        dto.setClienteId(clienteId);

        citaService.createCita(dto);
        return "redirect:/view/mis-citas";
    }
}
