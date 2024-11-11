package com.corhuila.FitManage.Controller;

import com.corhuila.FitManage.Entity.Cliente;
import com.corhuila.FitManage.Entity.Membresia;
import com.corhuila.FitManage.Entity.Plan;
import com.corhuila.FitManage.IService.IClienteService;
import com.corhuila.FitManage.IService.IMembresiaService;
import com.corhuila.FitManage.IService.INotificacionService;
import com.corhuila.FitManage.IService.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    @Autowired
    private IMembresiaService membresiaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IPlanService planService;
    @Autowired
    private  INotificacionService notificacionService;

    @GetMapping
    public List<Membresia> getAllMembresias() {
        return membresiaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membresia> getMembresiaById(@PathVariable Long id) {
        Optional<Membresia> membresia = membresiaService.findById(id);
        return membresia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/crear")
    public ResponseEntity<Membresia> createMembresia(
            @RequestParam Long clienteId,
            @RequestParam Long planId,
            @RequestParam(required = false) Integer mesesDuracion,
            @RequestParam(required = false) Double valor) { // Recibimos el valor

        // Buscar el cliente y el plan en la base de datos
        Optional<Cliente> clienteOpt = clienteService.findById(clienteId);
        Optional<Plan> planOpt = planService.findById(planId);

        if (clienteOpt.isPresent() && planOpt.isPresent()) {
            Membresia membresia = new Membresia();
            membresia.setCliente(clienteOpt.get());
            membresia.setPlan(planOpt.get());
            membresia.setFechaInicio(new Date()); // Fecha de inicio actual
            membresia.setEstado("Activa");

            // Calcular la fecha de fin si se proporciona duración en meses
            if (mesesDuracion != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(membresia.getFechaInicio());
                calendar.add(Calendar.MONTH, mesesDuracion);
                membresia.setFechaFin(calendar.getTime());
            } else {
                membresia.setFechaFin(null); // Si no se proporciona duración, mantener fechaFin en null
            }

            // Guardar el valor si se proporciona
            if (valor != null) {
                membresia.setValor(valor);
            }

            Membresia nuevaMembresia = membresiaService.save(membresia);

            // Preparar y enviar la notificación de creación de membresía
            String to = clienteOpt.get().getEmail();
            String subject = "¡Membresía creada exitosamente!";
            String message = String.format(
                    "Hola %s %s,\n\nTu membresía con el plan '%s' ha sido creada exitosamente.\n\n" +
                            "Descripción del plan:\n%s\n\n" +
                            "¡Gracias por ser parte de nuestra comunidad fitness!\n\n" +
                            "Saludos cordiales,\nEl equipo de FitManage",
                    clienteOpt.get().getNombre(),
                    clienteOpt.get().getApellido(),
                    membresia.getPlan().getNombrePlan(),
                    membresia.getPlan().getDescripcion()
            );

            try {
                notificacionService.sendEmailNotification(to, subject, message);
            } catch (Exception e) {
                // Log error or handle it as needed
            }

            return new ResponseEntity<>(nuevaMembresia, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Membresia> updateMembresia(@PathVariable Long id, @RequestBody Membresia membresia) {
        Optional<Membresia> existingMembresia = membresiaService.findById(id);
        if (!existingMembresia.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        membresia.setMembresiaId(id);
        Membresia updatedMembresia = membresiaService.save(membresia);
        return ResponseEntity.ok(updatedMembresia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembresia(@PathVariable Long id) {
        if (!membresiaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        membresiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
