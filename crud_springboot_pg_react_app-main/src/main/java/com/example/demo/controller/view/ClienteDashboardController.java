package com.example.demo.controller.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CitaDTO;
import com.example.demo.model.Cita;
import com.example.demo.model.Cliente;
import com.example.demo.repository.CitaRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ManicuristaRepository;
import com.example.demo.service.CitaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ClienteDashboardController {

    private static final Logger log = LoggerFactory.getLogger(ClienteDashboardController.class);

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
        try {
            String email = (String) session.getAttribute("userEmail");

            List<Cita> citas = citaRepository.findAll().stream()
                    .filter(c -> c.getCliente() != null && email.equals(c.getCliente().getEmail()))
                    .collect(Collectors.toList());

            model.addAttribute("citas", citas);
            model.addAttribute("manicuristas", manicuristaRepository.findAll());
            return "ClienteDashboard";
        } catch (Exception e) {
            log.error("Error al cargar mis citas", e);
            model.addAttribute("citas", List.of());
            model.addAttribute("manicuristas", manicuristaRepository.findAll());
            model.addAttribute("errorCarga", "Error al cargar las citas. Intenta de nuevo.");
            return "ClienteDashboard";
        }
    }

    @PostMapping("/mis-citas/agendar")
    public String agendarCita(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userEmail") == null) {
                return "redirect:/view/login";
            }

            String email = (String) session.getAttribute("userEmail");
            String fecha = request.getParameter("fecha");
            String horario = request.getParameter("horario");
            String manicuristaIdStr = request.getParameter("manicuristaId");

            log.info("agendarCita called - fecha={}, horario={}, manicuristaId={}, email={}", fecha, horario, manicuristaIdStr, email);

            if (manicuristaIdStr == null || manicuristaIdStr.isEmpty()) {
                return "redirect:/view/mis-citas?error=no-manicurista";
            }
            Long manicuristaId = Long.parseLong(manicuristaIdStr);

            LocalDate date = LocalDate.parse(fecha);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return "redirect:/view/mis-citas?error=finde";
            }

            LocalDateTime dateHour = LocalDateTime.parse(fecha + "T" + horario);

            Cliente cliente = clienteRepository.findByEmail(email).orElse(null);
            if (cliente == null) {
                log.error("Cliente no encontrado: {}", email);
                return "redirect:/view/mis-citas?error=general";
            }

            CitaDTO dto = new CitaDTO();
            dto.setDateHour(dateHour);
            dto.setManicuristaId(manicuristaId);
            dto.setClienteId(cliente.getId());
            dto.setState("PENDIENTE");

            citaService.createCita(dto);
            return "redirect:/view/mis-citas";
        } catch (Exception e) {
            log.error("Error al agendar cita", e);
            return "redirect:/view/mis-citas?error=general";
        }
    }
}
