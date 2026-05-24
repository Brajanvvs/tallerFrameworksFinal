package com.example.demo.controller.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.demo.dto.CitaDTO;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.service.CitaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/view/citas")
public class CitaViewController {

    private static final Logger log = LoggerFactory.getLogger(CitaViewController.class);

    private final CitaService citaService;
    private final ClienteRepository clienteRepository;
    private final ManicuristaRepository manicuristaRepository;

    public CitaViewController(CitaService citaService,
                              ClienteRepository clienteRepository,
                              ManicuristaRepository manicuristaRepository) {
        this.citaService = citaService;
        this.clienteRepository = clienteRepository;
        this.manicuristaRepository = manicuristaRepository;
    }

    @GetMapping
    public String listarCitas(Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
        model.addAttribute("citas", citaService.getAllCitas());
        model.addAttribute("cita", new CitaDTO());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        return "Cita";
    }

    @PostMapping("/guardar")
    public String guardarCita(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
        try {
            String idStr = request.getParameter("id");
            String fecha = request.getParameter("fecha");
            String horario = request.getParameter("horario");
            String state = request.getParameter("state");
            String clienteIdStr = request.getParameter("clienteId");
            String manicuristaIdStr = request.getParameter("manicuristaId");

            LocalDateTime dateHour = LocalDateTime.parse(fecha + "T" + horario);

            CitaDTO dto = new CitaDTO();
            if (idStr != null && !idStr.isEmpty()) {
                dto.setId(Long.parseLong(idStr));
            }
            dto.setDateHour(dateHour);
            dto.setState(state);
            dto.setClienteId(Long.parseLong(clienteIdStr));
            dto.setManicuristaId(Long.parseLong(manicuristaIdStr));

            if (dto.getId() == null) {
                citaService.createCita(dto);
            } else {
                citaService.updateCita(dto.getId(), dto);
            }
            return "redirect:/view/citas";
        } catch (Exception e) {
            log.error("Error al guardar cita", e);
            return "redirect:/view/citas";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
        CitaDTO cita = citaService.getCitaById(id);
        model.addAttribute("cita", cita);
        model.addAttribute("citas", citaService.getAllCitas());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("manicuristas", manicuristaRepository.findAll());
        if (cita.getDateHour() != null) {
            model.addAttribute("editFecha", cita.getDateHour().toLocalDate().toString());
            model.addAttribute("editHorario", cita.getDateHour().toLocalTime().toString().substring(0, 5));
        }
        return "Cita";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userName") == null) return "redirect:/view/login";
        citaService.deleteCita(id);
        return "redirect:/view/citas";
    }
}
