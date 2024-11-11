package com.corhuila.FitManage.Controller;


import com.corhuila.FitManage.Entity.Notificacion;
import com.corhuila.FitManage.IService.INotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    // Obtener todas las notificaciones
    @GetMapping
    public List<Notificacion> getAllNotificaciones() {
        return notificacionService.findAll();
    }

    // Obtener una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        Optional<Notificacion> notificacion = notificacionService.findById(id);
        return notificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva notificación
    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion savedNotificacion = notificacionService.save(notificacion);
        return new ResponseEntity<>(savedNotificacion, HttpStatus.CREATED);
    }

    // Enviar una notificación por correo electrónico
    @PostMapping("/enviar")
    public ResponseEntity<String> enviarNotificacionCorreo(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message) {

        try {
            notificacionService.sendEmailNotification(to, subject, message);
            return ResponseEntity.ok("Notificación enviada exitosamente por correo electrónico.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo: " + e.getMessage());
        }
    }

    // Eliminar una notificación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        if (!notificacionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notificacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
