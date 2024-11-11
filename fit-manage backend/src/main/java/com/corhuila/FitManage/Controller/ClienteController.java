package com.corhuila.FitManage.Controller;

import com.corhuila.FitManage.Dto.IClientreDto;
import com.corhuila.FitManage.Entity.Cliente;
import com.corhuila.FitManage.IService.IClienteService;
import com.corhuila.FitManage.IService.INotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final IClienteService clienteService;
    private final INotificacionService notificacionService;

    @Autowired
    public ClienteController(IClienteService clienteService, INotificacionService notificacionService) {
        this.clienteService = clienteService;
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getEmail() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Cliente savedCliente = clienteService.save(cliente);

        // Preparar y enviar la notificación de bienvenida
        String to = cliente.getEmail();
        String subject = "¡Bienvenido a la familia de nuestro Gimnasio!";
        String message = String.format(
                "Hola %s %s,\n\nEstamos muy emocionados de darte la bienvenida a nuestra comunidad fitness. ¡Es un gran paso hacia una vida más saludable y activa!\n\n" +
                        "Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarnos.\n\n" +
                        "¡Gracias por confiar en nosotros! Nos vemos en el gimnasio.\n\n" +
                        "Saludos cordiales,\nEl equipo de FitManage",
                cliente.getNombre(), cliente.getApellido()
        );

        try {
            notificacionService.sendEmailNotification(to, subject, message);
        } catch (Exception e) {
            // Log error or handle it as needed
        }

        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        Cliente updatedCliente = clienteService.save(cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validar/{email}/{password}")
    public ResponseEntity<Map<String, Object>> getValidar(@PathVariable String email, @PathVariable String password) {
        Optional<IClientreDto> cliente = clienteService.getValidar(email, password);

        if (cliente.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", true);
            response.put("id", cliente.get().getId());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
